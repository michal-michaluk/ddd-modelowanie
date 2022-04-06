package devices.configuration.device.events;

import devices.configuration.DomainEvent;
import devices.configuration.device.Location;
import lombok.Value;

@Value
public class LocationUpdated implements DomainEvent {
    String deviceId;
    Location location;
}
