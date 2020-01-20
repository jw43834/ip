package com.cl.edgegateway.outbound;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Component
@Getter
@Setter
public class OutboundManager {
    private LinkedBlockingQueue<Object> outboundQueue;
}
