package com.cl.edgegateway.common;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TCPAdopter extends NetworkAdopter {
    private ExecutorService executorService;
    private ServerSocket serverSocket;

    public TCPAdopter(NetworkAdopterInfo networkAdopterInfo) {
        super(networkAdopterInfo);
    }

    @Override
    public void initialize() throws IOException {
        // Connection Limit
        executorService = Executors.newFixedThreadPool(networkAdopterInfo.getConnectionLimit());

        try {
            // Init TCP Socket
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(networkAdopterInfo.getAdopterName(), networkAdopterInfo.getPort()));
        } catch (Exception e) {
            serverSocket.close();
            e.printStackTrace();
//            if (!serverSocket.isClosed()) {
//                log.error("ERROR: Can't bind Host Address..! Check it now..");
//                stopAdopter();
//            }
        }

        // Wait Connection
        log.debug("Waiting Connection on port" + "[" + networkAdopterInfo.getPort() + "]");

        while (true) {
            Socket socket = serverSocket.accept();

//            ChatThread chatThread = new ChatThread(sock, hm);
//            chatThread.start();

            executorService.submit(new TCPCallable("ThreadID",socket));


            // 커넥션이 맺어질때마다 각 Session Thread를 생성한다.
            // 해당 쓰레드는 새로 클래스를 생성하여.. 핸드쉐이킹 및 매인 함수를 만들자.
            try {
                // Create Session Thread
                // 여기서 쓰레드 생성 호출. 호출한 함수에서 내부 네트워크 작업 실행한다.
                // 자바에서는 어떻게 짜야하나 고민중..

                // 너무 C++ 스럽게 짜놨다... 어케 해야하나 ㅋㅋ

            } catch (Exception e) {
                log.debug("TCPAdopter>> Exception" + "[" + e.getMessage() + "]");
                log.debug("TCPAdopter>> ERROR : Failed to Create Each Session..!");
                stopAdopter();
            }
        }
    }

    public void stopAdopter() {
        // Close Each Sessions Socket..
        try {
            // 생성된 쓰레드 개수만큼의 Opened Socket을 Close 처리

        } catch (Exception e) {
            log.debug("TCPAdopter>> Exception" + "[" + e.getMessage() + "]");
            log.debug("TCPAdopter>> ERROR : Failed to Close Each Session..!");
        }
    }

    @Override
    public void send() {

    }

    @Override
    public void receive() {

    }
}

//class ChatThread extends Thread{
//    private Socket sock;
//    private String id;
//    private BufferedReader br;
//    private HashMap<String, Object> hm;
//    private boolean initFlag = false;
//    public ChatThread(Socket sock, HashMap<String,Object> hm) {
//        this.sock = sock;
//        this.hm = hm;
//        try {
//            PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
//            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//            id = br.readLine();
//            broadcast(id + "님이 접속하셨습니다.");
//            System.out.println("접속한 사용자의 아이디 : "+id);
//            synchronized (hm) {
//                hm.put(this.id, pw);
//            }
//            initFlag = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    public void run() {
//        try {
//            String line = null;
//            while((line = br.readLine()) != null) {
//                if(line.equals("/quit")) {
//                    break;
//                }
//                if(line.indexOf("/to") == 0) {
//                    sendmsg(line);
//                }else {
//                    broadcast(id+" : "+line);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            synchronized (hm) {
//                hm.remove(id);
//            }
//            broadcast(id+"님이 접속을 종료했습니다.");
//            try {
//                if(sock != null) {
//                    sock.close();
//                }
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//    }
//    public void sendmsg(String msg) {
//        int start = msg.indexOf(" ") + 1;
//        int end = msg.indexOf(" ",start);
//        if(end != -1) {
//            String to = msg.substring(start, end);
//            String msg2 = msg.substring(end +1);
//            Object obj = hm.get(to);
//            if(obj != null) {
//                PrintWriter pw = (PrintWriter)obj;
//                pw.println(id + "님이 다음의 귓속말을 보내셨습니다. : " + msg2);
//                pw.flush();
//            }
//        }
//    }
//    public void broadcast(String msg) {
//        synchronized (hm) {
//            Collection<Object> collection = hm.values();
//            Iterator<?> iter = collection.iterator();
//            while(iter.hasNext()) {
//                PrintWriter pw = (PrintWriter)iter.next();
//                pw.println(msg);
//                pw.flush();
//            }
//        }
//    }
//}