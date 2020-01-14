package com.cl.edgegateway.adopter.tcp;

import com.cl.edgegateway.common.NetworkAdopter;
import com.cl.edgegateway.common.NetworkAdopterInfo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TCPAdopter extends NetworkAdopter {
    private ExecutorService executorService;
    private ServerSocket serverSocket;
    private static int threadId = 1;

    public TCPAdopter(NetworkAdopterInfo networkAdopterInfo) {
        super(networkAdopterInfo);
        initialize();
    }

    @Override
    public void initialize() {
        log.debug("TCP Adopter Initialize");
        // Connection Limit
        executorService = Executors.newFixedThreadPool(networkAdopterInfo.getConnectionLimit());

        try {
            // Init TCP Socket
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(networkAdopterInfo.getAdopterName(), networkAdopterInfo.getPort()));
        } catch (Exception e) {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
//            if (!serverSocket.isClosed()) {
//                log.error("ERROR: Can't bind Host Address..! Check it now..");
//                stopAdopter();
//            }
        }

        // Wait Connection
        log.debug("Waiting Connection on port" + "[" + networkAdopterInfo.getPort() + "]");

        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();

                executorService.submit(new TCPCallable("Thread_Id_" + Integer.toString(threadId++), socket));
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {

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