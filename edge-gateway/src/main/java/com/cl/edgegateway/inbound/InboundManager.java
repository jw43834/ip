package com.cl.edgegateway.inbound;

import com.cl.edgegateway.adopter.tcp.TCPAdopter;
import com.cl.edgegateway.adopter.common.NetworkAdopter;
import com.cl.edgegateway.adopter.common.NetworkAdopterInfo;
import com.cl.edgegateway.adopter.common.NetworkAdopterType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Slf4j
@Component
public class InboundManager {
    private ArrayList<NetworkAdopter> inboundAdopterList;

    @PostConstruct
    public void initialize() {
        log.debug("Inbound Manager Initialize");

        inboundAdopterList = new ArrayList<NetworkAdopter>();
        // TODO : DB에서 어댑터 설정정보 조회 -> 어댑터 초기화
        ArrayList<NetworkAdopterInfo> genAdopterList = new ArrayList<NetworkAdopterInfo>();
        NetworkAdopterInfo tcpAdopterInfo = NetworkAdopterInfo.builder()
                .adopterId("tcp1")
                .adopterName("localhost")
                .networkAdopterType(NetworkAdopterType.TCP)
                .connectionLimit(100)
                .connectionTimeout(1000)
                .port(9999)
                .build();
        genAdopterList.add(tcpAdopterInfo);

        for (NetworkAdopterInfo networkAdopterInfo : genAdopterList) {
            switch (networkAdopterInfo.getNetworkAdopterType()) {
                case TCP: {
                    NetworkAdopter tcpAdopter = new TCPAdopter(networkAdopterInfo);
                    inboundAdopterList.add(tcpAdopter);
                    break;
                }
                case BLE: {
                    break;
                }
                case UDP:
                    break;
                case MODBUS_RTU:
                    break;
                case MODBUS_TCP:
                    break;
            }
        }
    }
}
