package com.cl.edgegateway.common;

import lombok.*;

//@Table(name="adopters")
//@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NetworkAdopterInfo {
    private NetworkAdopterType networkAdopterType;
    private String adopterId;
    private String adopterName;
    private int port;
    private int connectionTimeout;
    private int connectionLimit;
}
