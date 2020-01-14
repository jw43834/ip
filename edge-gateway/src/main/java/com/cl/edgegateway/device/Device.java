package com.cl.edgegateway.device;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="device")
public class Device {
    @Id
    @Column(name="device_id")
    private String deviceId;
    @Column(name="device_sequence")
    private int deviceSequence;
    @Column(name="device_name")
    private String deviceName;
    @Column(name="password")
    private String password;
}
