package com.cl.edgegateway.device;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService{
    private final DeviceRepository deviceRepository;
    @Override
    public Page<Device> findAll(Pageable pageable) {
        return deviceRepository.findAll(pageable);
    }

    @Override
    public Optional<Device> findById(String deviceId) {
        return deviceRepository.findById(deviceId);
    }

    @Override
    public void save(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public void delete(String deviceId) {
        Device device = new Device().builder().deviceId(deviceId).build();
        deviceRepository.delete(device);
    }
}
