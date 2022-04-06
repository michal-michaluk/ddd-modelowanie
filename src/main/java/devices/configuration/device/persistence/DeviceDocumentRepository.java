package devices.configuration.device.persistence;

import devices.configuration.device.Device;
import devices.configuration.device.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class DeviceDocumentRepository implements DeviceRepository {

    private final DeviceDocumentJpaRepository repository;

    @Override
    public Page<Device> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(DeviceDocumentEntity::getDevice);
    }

    @Override
    public Optional<Device> findByDeviceId(String deviceId) {
        return repository.findById(deviceId)
                .map(DeviceDocumentEntity::getDevice);
    }

    @Override
    public void save(Device device) {
        repository.findById(device.deviceId())
                .ifPresent(e -> e.setDevice(device));
    }
}
