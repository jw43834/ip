package com.cl.edgegateway.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends JpaRepository<Device, String> {
}
