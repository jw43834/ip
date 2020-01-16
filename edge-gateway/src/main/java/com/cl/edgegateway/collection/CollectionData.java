package com.cl.edgegateway.collection;

import com.cl.edgegateway.device.Device;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@ToString
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="collection_data")
public class CollectionData {
    @Id
    @Column(name="collection_data_sequence")
    private long collectionDataSequence;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    private Date occationTime;

    private Map<String, Object> sensorData;
}
