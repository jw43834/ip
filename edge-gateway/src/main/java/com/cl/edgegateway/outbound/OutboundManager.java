package com.cl.edgegateway.outbound;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Component
public class OutboundManager {
    private LinkedBlockingQueue<Object> outboundQueue;

    public void setOutboundQueue(LinkedBlockingQueue<Object> outboundQueue) {
        this.outboundQueue = outboundQueue;
    }
}
