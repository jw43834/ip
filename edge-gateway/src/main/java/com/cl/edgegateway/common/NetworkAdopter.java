package com.cl.edgegateway.common;

import java.io.IOException;

//@Table(name="adopters")
//@Entity
public abstract class NetworkAdopter {
    protected NetworkAdopterInfo networkAdopterInfo;

    public NetworkAdopter(NetworkAdopterInfo networkAdopterInfo) {
        this.networkAdopterInfo = networkAdopterInfo;
    }

    abstract public void initialize() throws IOException;

    abstract public void send();

    abstract public void receive();
}
