package com.cl.edgegateway.common.message;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
public class MessageHeader implements Serializable {
    // Message Type : 0-auth, 1-data
    private MessageType messageType;
    // Content length
    private int payloadLength;
}


