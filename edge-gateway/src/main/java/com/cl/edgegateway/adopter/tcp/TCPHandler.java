package com.cl.edgegateway.adopter.tcp;

import com.cl.edgegateway.adopter.common.NetworkAdopterInfo;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TCPHandler implements Callable<String> {
    private NetworkAdopterInfo networkAdopterInfo;
    private ServerSocket serverSocket;
    private ExecutorService executorService;

    private static int threadId = 1;

    public TCPHandler(NetworkAdopterInfo networkAdopterInfo, ServerSocket serverSocket) {
        this.networkAdopterInfo = networkAdopterInfo;
        this.serverSocket = serverSocket;
    }

    public void initialize(){
        log.debug("TCP Handler initialize");
        executorService = Executors.newFixedThreadPool(networkAdopterInfo.getConnectionLimit());
    }

    @Override
    public String call() throws Exception {
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
                //stopAdopter();
            }
        }
    }
}
