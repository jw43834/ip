package com.cl.edgegateway.sensor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, String> {
}
