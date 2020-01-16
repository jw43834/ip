package com.cl.edgegateway.device;

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
@RequestMapping("/device")
public class DeviceController {
    private final DeviceService deviceService;

    @GetMapping
    public Page<Device> getDevices(Pageable pageable) {
        Page<Device> devices = deviceService.findAll(pageable);

        return devices;
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity getDevice(@PathVariable String deviceId) {

        Optional<Device> device = deviceService.findById(deviceId);
        return ResponseEntity.ok(device);
    }

    @PostMapping
    public ResponseEntity saveDevice(@RequestBody @Valid Device device, Errors erros) {
        deviceService.save(device);
        return ResponseEntity.ok(device);
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity editDevice(@RequestBody @Valid Device device, Errors erros) {
        deviceService.save(device);
        return ResponseEntity.ok(device);
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity deleteDevice(@RequestBody @Valid Device device, Errors erros) {
        deviceService.save(device);
        return ResponseEntity.ok(device);
    }
}