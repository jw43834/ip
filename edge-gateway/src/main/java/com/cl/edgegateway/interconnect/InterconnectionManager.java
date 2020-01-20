package com.cl.edgegateway.interconnect;

import com.cl.edgegateway.adopter.common.NetworkManager;
import com.cl.edgegateway.inbound.InboundManager;
import com.cl.edgegateway.outbound.OutboundManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Service
@RequiredArgsConstructor
public class InterconnectionManager implements NetworkManager {
    private final InboundManager inboundManager;

    private final OutboundManager outboundManager;

    private LinkedBlockingQueue<Object> outboundQueue;

    @PostConstruct
    @Override
    public void initialize() {
        outboundQueue = new LinkedBlockingQueue<Object>();
        log.debug("Interconnection Manager Initialize");
        // TODO : outboundManager 생성 후 큐 인젝션
        outboundManager.setOutboundQueue(outboundQueue);

        //queue.offer();    // put
        //queue.take();     // get
    }

    // TODO : Inbound Manager 1) Pre Processing -> 2) Routing(각 Outbound Adopter enqueue)
    // TODO : Throughput 조절(큐 사이즈)
    // Inbound에서 전달받은 메시지를 Interconnection에서 큐처리 하고 Adopter를 결정해서 보내는 식
    // Outbound로 보낼 대상 데이터, 설정정보 등이 있어야 함
    // 각각의 OutboundAdopter에도 큐를 넣을지는 고민...
}
