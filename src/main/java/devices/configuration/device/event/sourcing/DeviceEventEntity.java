package devices.configuration.device.event.sourcing;

import devices.configuration.DomainEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Table(name = "device_events")
@NoArgsConstructor
class DeviceEventEntity {
    @Id
    private UUID id;
    private String deviceId;
    private String type;
    private Instant time;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private DomainEvent event;

    public DeviceEventEntity(String deviceId, DomainEvent event) {
        EventTypes.Type type = EventTypes.of(event);
        this.id = UUID.randomUUID();
        this.deviceId = deviceId;
        this.type = type.getType();
        this.time = Instant.now();
        this.event = event;
    }
}
