package com.cl.edgegateway.adopter.tcp;

import com.cl.edgegateway.adopter.common.NetworkAdopter;
import com.cl.edgegateway.adopter.common.NetworkAdopterInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TCPAdopter extends NetworkAdopter {
    private ExecutorService tcpHandlerThreadPool;

    private final static int handlerCount = 1;

    private ServerSocket serverSocket;

    public TCPAdopter(NetworkAdopterInfo networkAdopterInfo) {
        super(networkAdopterInfo);
        initialize();
    }

    @Override
    public void initialize() {
        tcpHandlerThreadPool = Executors.newFixedThreadPool(handlerCount);

        log.debug("TCP Adopter Initialize");

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
        }

        TCPHandler tcpHandler = new TCPHandler(networkAdopterInfo, serverSocket);
        tcpHandler.initialize();

        tcpHandlerThreadPool.submit(tcpHandler);
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