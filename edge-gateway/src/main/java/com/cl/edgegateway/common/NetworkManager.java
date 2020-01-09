package com.cl.edgegateway.common;

import javax.annotation.PostConstruct;

public interface NetworkManager {
    @PostConstruct
    void initialize();
}
