package ru.nvg.printservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nvg.printservice.dao.DeviceRepository;
import ru.nvg.printservice.dao.UserRepository;
import ru.nvg.printservice.domain.Device;
import ru.nvg.printservice.domain.User;

@Slf4j
@RestController
@RequestMapping(DeviceController.BASE_URL)
public class DeviceController {
    public static final String BASE_URL = "/api/v1/devices";

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceController(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @PostMapping
    @ResponseBody
    public Device saveJobBulk(@RequestBody @Validated String deviceName) {
        Device device = new Device();
        device.setName(deviceName);
        return deviceRepository.save(device);
    }
}
