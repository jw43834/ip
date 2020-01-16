package com.cl.edgegateway.adopter.common;

import lombok.Getter;

import java.io.IOException;

//@Table(name="adopters")
//@Entity
@Getter
public abstract class NetworkAdopter {
    protected NetworkAdopterInfo networkAdopterInfo;

    public NetworkAdopter(NetworkAdopterInfo networkAdopterInfo) {
        this.networkAdopterInfo = networkAdopterInfo;
    }


    abstract public void initialize() throws IOException;

    abstract public void send();

    abstract public void receive();
}
