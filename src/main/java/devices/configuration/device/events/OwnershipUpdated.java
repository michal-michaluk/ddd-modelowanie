package devices.configuration.device.events;

import devices.configuration.DomainEvent;
import devices.configuration.device.Ownership;
import lombok.Value;

@Value
public class OwnershipUpdated implements DomainEvent {
    String deviceId;
    Ownership ownership;
}
