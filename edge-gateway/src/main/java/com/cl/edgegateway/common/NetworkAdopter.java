package com.cl.edgegateway.common;

//@Table(name="adopters")
//@Entity
public abstract class NetworkAdopter {
    protected NetworkAdopterInfo networkAdopterInfo;

    public NetworkAdopter(NetworkAdopterInfo networkAdopterInfo) {
        this.networkAdopterInfo = networkAdopterInfo;
    }

    abstract public void initialize();

    abstract public void send();

    abstract public void receive();
}
