package com.cl.edgegateway.outbound;

import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Component
public class OutboundManager {
    private LinkedBlockingQueue<Object> outboundQueue;

    public void setOutboundQueue(LinkedBlockingQueue<Object> outboundQueue) {
        this.outboundQueue = outboundQueue;
    }
}
