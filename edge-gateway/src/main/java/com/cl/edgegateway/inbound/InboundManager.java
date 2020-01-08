package com.cl.edgegateway.inbound;

import com.cl.edgegateway.common.NetworkAdopter;
import com.cl.edgegateway.common.NetworkAdopterInfo;
import com.cl.edgegateway.common.NetworkAdopterType;
import com.cl.edgegateway.common.TCPAdopter;

import java.util.ArrayList;

public class InboundManager {
    private ArrayList<NetworkAdopter> inboundAdopterList;

    public void initialize() {
        // TODO : DB에서 어댑터 설정정보 조회 -> 어댑터 초기화
        ArrayList<NetworkAdopterInfo> genAdopterList = new ArrayList<NetworkAdopterInfo>();
        NetworkAdopterInfo tcpAdopterInfo = NetworkAdopterInfo.builder()
                .adopterId("tcp1")
                .adopterName("tcp1")
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
