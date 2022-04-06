package devices.configuration.device.event.sourcing;

import devices.configuration.DomainEvent;
import devices.configuration.device.Device;
import devices.configuration.device.DeviceRepository;
import devices.configuration.device.Settings;
import devices.configuration.device.events.LocationUpdated;
import devices.configuration.device.events.OpeningHoursUpdated;
import devices.configuration.device.events.OwnershipUpdated;
import devices.configuration.device.events.SettingsUpdated;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DeviceEventSourcedRepository implements DeviceRepository {

    private final DeviceEventRepository repository;

    @Override
    public Page<Device> findAll(Pageable pageable) {
        // todo it will be inefficient to do reads from events
        return Page.empty(pageable);
    }

    @Override
    public Optional<Device> findByDeviceId(String deviceId) {
        List<DomainEvent> events = repository.findAllDomainEvents(deviceId);
        if (events.isEmpty()) {
            return Optional.empty();
        }
        LastEvents last = LastEvents.fromHistoryOf(events);
        Device device = new Device(deviceId, new ArrayList<>(),
                last.getOrNull(OwnershipUpdated.class, OwnershipUpdated::getOwnership),
                last.getOrNull(LocationUpdated.class, LocationUpdated::getLocation),
                last.getOrNull(OpeningHoursUpdated.class, OpeningHoursUpdated::getOpeningHours),
                last.getOrDefault(SettingsUpdated.class, SettingsUpdated::getSettings, Settings.defaultSettings())
        );
        return Optional.of(device);
    }

    @Override
    public void save(Device device) {
        List<DomainEvent> events = clearEvents(device);
        events.forEach(event -> repository.save(new DeviceEventEntity(id(device), event)));
    }
}
