package com.cl.edgegateway.sensor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface SensorService {
    Page<Sensor> findAll(Pageable pageable);
    Optional<Sensor> findById(String sensorId);
    void save(Sensor sensor);
    void delete(String sensorId);
}
