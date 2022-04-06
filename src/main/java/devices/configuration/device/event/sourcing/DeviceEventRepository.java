package devices.configuration.device.event.sourcing;

import devices.configuration.DomainEvent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public interface DeviceEventRepository extends CrudRepository<DeviceEventEntity, UUID> {

    @Query(value = "select distinct on (type) *" +
            " from device_events" +
            " where device_id = :deviceId" +
            " order by type, time desc", nativeQuery = true)
    List<DeviceEventEntity> findByDeviceId(String deviceId);

    default List<DomainEvent> findAllDomainEvents(String deviceId) {
        return findByDeviceId(deviceId).stream()
                .map(DeviceEventEntity::getEvent)
                .map(LegacyDomainEvent::normalise)
                .collect(Collectors.toList());
    }
}
