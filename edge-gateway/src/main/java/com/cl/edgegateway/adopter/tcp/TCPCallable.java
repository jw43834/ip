package com.cl.edgegateway.adopter.tcp;

import com.cl.edgegateway.collection.CollectionData;
import com.cl.edgegateway.common.BeanUtils;
import com.cl.edgegateway.device.Device;
import com.cl.edgegateway.device.DeviceService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.SerializationUtils;

import java.io.*;
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
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        // TODO : 패킷 정의 필요
        // TODO : 첫 전송 데이터 -> 인증패킷. 인증, 수집데이터 스키마 정의 필요. 크기많큼 데이터 Read
        byte[] byteArr = new byte[1000];
        int readByteCount = inputStream.read(byteArr);

        log.debug("readByteCount : {}",readByteCount);

        // 초기 패킷 데이터 Read
        Device device = null;
        try {
            device = (Device) SerializationUtils.deserialize(byteArr);
        }catch(Exception e){
            e.printStackTrace();
        }
        log.debug("received data : {}",device);

        // Device 정보 조회
        Device searchDevice = deviceService.findById(device.getDeviceId()).get();

        if(searchDevice == null)
            log.debug("미등록된 장치");
        else if(!device.getPassword().equals(searchDevice.getPassword())){
            log.debug("인증 실패");
            socket.close();
            // TODO : 쓰레드 삭제
            return threadName;
        } else
            log.debug("Device Id({}) Connected",device.getDeviceId());

        while(true){
            readByteCount = inputStream.read(byteArr);

            log.debug("readByteCount : {}",readByteCount);

            CollectionData collectionData = (CollectionData) SerializationUtils.deserialize(byteArr);

                // TODO : 큐나 전처리기 등으로 데이터 전달
        }

        //String data = new String(byteArr, 0, readByteCount, "UTF-8");
//        try {
//            String line = null;
//            while((line = bufferedReader.readLine()) != null) {
//                if(line.equals("/quit")) {
//                    break;
//                }
//                if(line.indexOf("/to") == 0) {
//                    sendmsg(line);
//                }else {
//                    //bufferedReaderoadcast(id+" : "+line);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                if(socket != null) {
//                    socket.close();
//                    log.debug("[{}] TCP thread closed",threadName);
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
    }

    public void sendmsg(String sendDataString) {
        log.debug("send message : "+sendDataString);
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
