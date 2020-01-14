package com.cl.edgegateway.device;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DeviceService {
    Page<Device> findAll(Pageable pageable);
    Optional<Device> findById(String deviceId);
    void save(Device device);
    void delete(String deviceId);
}
