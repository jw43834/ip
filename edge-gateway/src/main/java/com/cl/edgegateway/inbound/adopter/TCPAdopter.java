package com.cl.edgegateway.inbound.adopter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPAdopter implements InboundAdopter {
//public class TCPAdopter {

    private final Logger log = LoggerFactory.getLogger(getClass());
    ExecutorService executorService;
    ServerSocket serverSocket;

    // 커넥션이 맺어질 list
    //List<Client> connection = new Vector<Client>();

    // 설정파일에 따로 빼놔야함.
    String hostName = "localhost";
    int tcpPort = 5001;

    @Override
    // TCP Adopter Start
    public void startAdopter() {
        // Create ThreadPool
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {
            // Init TCP Socket
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(hostName, tcpPort));
        } catch (Exception e) {
            if (!serverSocket.isClosed()) {
                log.debug("ERROR: Can't bind Host Address..! Check it now..");
                stopAdopter();
            }
        }

        // Wait Connection
        log.debug("TCPAdopter>> Listening on port" + "[" + tcpPort + "]");

        while (true) {

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

    @Override
    public void stopAdopter() {
        // Close Each Sessions Socket..
        try {
            // 생성된 쓰레드 개수만큼의 Opened Socket을 Close 처리

        } catch (Exception e) {
            log.debug("TCPAdopter>> Exception" + "[" + e.getMessage() + "]");
            log.debug("TCPAdopter>> ERROR : Failed to Close Each Session..!");
        }
    }
}
