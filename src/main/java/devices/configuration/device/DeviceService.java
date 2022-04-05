package devices.configuration.device;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository repository;

    @Transactional
    public Optional<DeviceSnapshot> update(String deviceId, UpdateDevice update) {
        return repository.findByDeviceId(deviceId)
                .map(device -> {
                    update.apply(device);
                    repository.save(device);
                    return device.toSnapshot();
                });
    }
}