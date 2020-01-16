package com.cl.edgegateway.adopter.common;

import javax.annotation.PostConstruct;

public interface NetworkManager {
    @PostConstruct
    void initialize();
}
