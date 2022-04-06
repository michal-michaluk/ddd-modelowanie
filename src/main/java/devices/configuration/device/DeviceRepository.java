package devices.configuration.device;

import devices.configuration.DomainEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository {
    Page<Device> findAll(Pageable pageable);

    Optional<Device> findByDeviceId(String deviceId);

    void save(Device device);

    default String id(Device device) {
        return device.deviceId;
    }

    default List<DomainEvent> clearEvents(Device device) {
        List<DomainEvent> events = List.copyOf(device.events);
        device.events.clear();
        return events;
    }
}
