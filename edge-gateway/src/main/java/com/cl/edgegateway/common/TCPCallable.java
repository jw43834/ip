package com.cl.edgegateway.common;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;

@Slf4j
public class TCPCallable implements Callable<String> {
    private String threadName;
    private Socket socket;
    private BufferedReader br;

    public TCPCallable() {

    }

    public TCPCallable(String threadName, Socket socket) {
        this.threadName = threadName;
        this.socket = socket;
    }

    public void initialize(){
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            log.debug("[{}] TCP thread created",threadName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        try {
            String line = null;
            while((line = br.readLine()) != null) {
                if(line.equals("/quit")) {
                    break;
                }
                if(line.indexOf("/to") == 0) {
                    sendmsg(line);
                }else {
                    //broadcast(id+" : "+line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(socket != null) {
                    socket.close();
                    log.debug("[{}] TCP thread closed",threadName);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return null;
    }

    public void sendmsg(String msg) {
        int start = msg.indexOf(" ") + 1;
        int end = msg.indexOf(" ",start);
        if(end != -1) {
            String to = msg.substring(start, end);
            String msg2 = msg.substring(end +1);
//            Object obj = hm.get(to);
//            if(obj != null) {
//                PrintWriter pw = (PrintWriter)obj;
//                pw.println(msg2);
//                pw.flush();
//            }
        }
    }
}
