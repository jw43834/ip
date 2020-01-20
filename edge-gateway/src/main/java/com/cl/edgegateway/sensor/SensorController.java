package com.cl.edgegateway.sensor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/sensor")
public class SensorController {
    private final SensorService sensorService;

    @GetMapping
    public Page<Sensor> getSensors(Pageable pageable) {
        Page<Sensor> sensors = sensorService.findAll(pageable);

        return sensors;
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity getSensor(@PathVariable String sensorId) {

        Optional<Sensor> sensor = sensorService.findById(sensorId);
        return ResponseEntity.ok(sensor);
    }

    @PostMapping
    public ResponseEntity saveSensor(@RequestBody @Valid Sensor sensor, Errors erros) {
        sensorService.save(sensor);
        return ResponseEntity.ok(sensor);
    }

    @PutMapping("/{sensorId}")
    public ResponseEntity editSensor(@RequestBody @Valid Sensor sensor, Errors erros) {
        sensorService.save(sensor);
        return ResponseEntity.ok(sensor);
    }

    @DeleteMapping("/{sensorId}")
    public ResponseEntity deleteSensor(@RequestBody @Valid Sensor sensor, Errors erros) {
        sensorService.save(sensor);
        return ResponseEntity.ok(sensor);
    }
}