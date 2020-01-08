package com.cl.edgegateway.outbound;

import java.util.concurrent.LinkedBlockingQueue;

public class OutboundManager {
    private LinkedBlockingQueue<Object> outboundQueue;

    public void setOutboundQueue(LinkedBlockingQueue<Object> outboundQueue) {
        this.outboundQueue = outboundQueue;
    }
}
