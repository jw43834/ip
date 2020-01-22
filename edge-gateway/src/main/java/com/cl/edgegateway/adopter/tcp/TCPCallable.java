package com.cl.edgegateway.adopter.tcp;

import com.cl.edgegateway.collection.CollectionData;
import com.cl.edgegateway.common.BeanUtils;
import com.cl.edgegateway.common.CommonConstant;
import com.cl.edgegateway.common.message.MessageHeader;
import com.cl.edgegateway.common.message.MessageType;
import com.cl.edgegateway.device.Device;
import com.cl.edgegateway.device.DeviceService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.SerializationUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

@NoArgsConstructor
@Slf4j
public class TCPCallable implements Callable<String> {
    private DeviceService deviceService;
    private String threadName;
    private Socket socket;
    private InputStream inputStream;
    private BufferedReader bufferedReader;

    public TCPCallable(String threadName, Socket socket) {
        this.threadName = threadName;
        this.socket = socket;

        initialize();
    }

    public void initialize() {
        log.debug("Inbound TCP Adopter Initialize");

        try {
            deviceService = (DeviceService) BeanUtils.getBean("deviceServiceImpl");

            inputStream = socket.getInputStream();
            //bufferedReader = new BufferedReader(inputStream);
            log.debug("[{}] TCP thread created", threadName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws IOException {
        // TODO : 첫 전송 데이터 -> 인증패킷. 인증, 수집데이터 스키마 정의 필요. 크기많큼 데이터 Read
        byte[] byteArr = new byte[CommonConstant.messageHeaderSize];
        int readByteCount = inputStream.read(byteArr);

        log.debug("readByteCount : {}", readByteCount);

        // 초기 패킷 데이터(인증) Read
        MessageHeader commonMessage = null;
        try {
            commonMessage = (MessageHeader) SerializationUtils.deserialize(byteArr);

            // Message Header Type Check
            if (!commonMessage.getMessageType().equals(MessageType.AUTH)) {
                log.debug("Received Message is not Auth type");
                socket.close();
                return threadName;
            } else {
                // Device Auth
                byte[] payloadArr = new byte[commonMessage.getPayloadLength()];
                readByteCount = inputStream.read(payloadArr);

                log.debug("readByteCount : {}", readByteCount);

                Device device = null;
                try {
                    device = (Device) SerializationUtils.deserialize(payloadArr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                log.debug("received data : {}", device);

                // TODO : Caching Device Info
                // TODO : Null handling
                Device searchDevice = deviceService.findById(device.getDeviceId()).get();

                if (searchDevice == null)
                    log.debug("Unregistered device");
                else if (!device.getPassword().equals(searchDevice.getPassword())) {
                    log.debug("Fail device auth");
                    socket.close();
                    return threadName;
                } else
                    log.debug("Device Id({}) Connected", device.getDeviceId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Message deserialize failed");
            socket.close();
            return threadName;
        }

        while (true) {
            readByteCount = inputStream.read(byteArr);

            log.debug("readByteCount : {}", readByteCount);

            CollectionData collectionData = (CollectionData) SerializationUtils.deserialize(byteArr);
            log.debug("Read Data : {}", collectionData);

            // TODO : 큐나 전처리기 등으로 데이터 전달
        }
    }

    public void sendmsg(String sendDataString) {
        log.debug("send message : " + sendDataString);
        try {
            byte[] byteArr = sendDataString.getBytes("UTF-8");
            OutputStream os = socket.getOutputStream();
            os.write(byteArr);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
