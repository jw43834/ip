package com.cl.edgegateway.collection;

import com.cl.edgegateway.device.Device;
import com.cl.edgegateway.sensor.Sensor;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;

@ToString
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name="collection_data")
public class CollectionData {
    @Id
    //@Column(name="collection_data_sequence")
    private Long collectionDataSequence;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    // Sensor Mapping
    @ManyToOne
    //@JoinColumn(name = "device_id")
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    @Column(name="occurrence_time")
    private Date occurrenceTime;

    private HashMap<String, Double> measured_data;
}
