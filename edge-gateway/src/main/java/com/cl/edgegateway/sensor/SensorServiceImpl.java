package com.cl.edgegateway.sensor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService{
    private final SensorRepository sensorRepository;
    @Override
    public Page<Sensor> findAll(Pageable pageable) {
        return sensorRepository.findAll(pageable);
    }

    @Override
    public Optional<Sensor> findById(String sensorId) {
        return sensorRepository.findById(sensorId);
    }

    @Override
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Override
    public void delete(String sensorId) {
        Sensor sensor = new Sensor().builder().sensorId(sensorId).build();
        sensorRepository.delete(sensor);
    }
}
