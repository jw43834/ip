package com.cl.edgegateway.adopter.tcp;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;

@Slf4j
public class TCPCallable implements Callable<String> {
    private String threadName;
    private Socket socket;
    private InputStream inputStream;
    private BufferedReader bufferedReader;

    public TCPCallable(String threadName, Socket socket) {
        this.threadName = threadName;
        this.socket = socket;
    }

    public TCPCallable() {

    }

    public void initialize() {
        log.debug("Inbound TCP Adopter Initialize");
        try {
            inputStream = socket.getInputStream();
            //bufferedReader = new BufferedReader(inputStream);
            log.debug("[{}] TCP thread created", threadName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        // TODO : Input에 대한 스키마 정의 필요. 크기많큼 데이터 Read
        byte[] byteArr = new byte[100];
        int readByteCount = inputStream.read(byteArr);

        log.debug("readByteCount : {}",readByteCount);

        // 수신된 데이터 내부 유통 객체로 변환
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

        return null;
    }

    public void sendmsg(String sendDataString) throws UnsupportedEncodingException {
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
