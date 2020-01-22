package com.cl.edgegateway;

import com.cl.edgegateway.common.message.MessageHeader;
import com.cl.edgegateway.common.message.MessageType;
import com.cl.edgegateway.device.Device;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.SerializationUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

@Slf4j
public class TCPClientTest {
    public static void main(String[] arg) {
        Socket socket = null;            //Server와 통신하기 위한 Socket
        BufferedReader in = null;        //Server로부터 데이터를 읽어들이기 위한 입력스트림
        BufferedReader in2 = null;        //키보드로부터 읽어들이기 위한 입력스트림
        PrintWriter out = null;            //서버로 내보내기 위한 출력 스트림
        InetAddress ia = null;
        InputStream inputStream = null;
        OutputStream os = null;
        try {
            ia = InetAddress.getByName("localhost");    //서버로 접속
            socket = new Socket(ia, 9999);
            inputStream = socket.getInputStream();
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            os = socket.getOutputStream();

            System.out.println(socket.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            log.debug("Prepare to Send Data");
            Device device = Device.builder()
                    .deviceId("device1")
                    .deviceName("device1")
                    .deviceSequence(1L)
                    .password("11111111")
                    .build();
            log.debug("Send To Server : {}",device);

            byte[] payloadByte = SerializationUtils.serialize(device);

            MessageHeader messageHeader = MessageHeader.builder()
                    .messageType(MessageType.AUTH)
                    .payloadLength(payloadByte.length)
                    .build();

            byte[] messageHeaderByte = SerializationUtils.serialize(messageHeader);
            log.debug("message size : "+ messageHeaderByte.length);
            int messageLength = messageHeaderByte.length+payloadByte.length;
            byte[] sendData = new byte[messageLength];

            // src, srcP
            System.arraycopy(messageHeaderByte,0, sendData, 0, messageHeaderByte.length);
            System.arraycopy(payloadByte,0, sendData, messageHeaderByte.length, payloadByte.length);

            os.write(sendData);
            os.flush();

            out.close();
            socket.close();
        } catch (IOException e) {
        }
    }
}
