package com.cl.edgegateway.adopter.common;

public enum NetworkAdopterType {
    MODBUS_TCP("modbus_tcp"),
    MODBUS_RTU("modbus_rtu"),
    BLE("ble"),
    TCP("tcp"),
    UDP("udp"),
    WEBSOCKET("websocket");

    NetworkAdopterType(String type) {
    }
}
