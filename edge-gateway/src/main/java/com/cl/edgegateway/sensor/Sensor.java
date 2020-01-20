package com.cl.edgegateway.sensor;

import com.cl.edgegateway.device.Device;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sensor")
public class Sensor {
    @Id
    @Column(name = "sensor_id")
    private String sensorId;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @Column(name = "sensor_sequence")
    private int sensorSequence;

    @Column(name = "sensor_name")
    private String sensorName;
}
