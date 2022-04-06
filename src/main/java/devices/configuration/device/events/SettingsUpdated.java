package devices.configuration.device.events;

import devices.configuration.DomainEvent;
import devices.configuration.device.Settings;
import lombok.Value;

@Value
public class SettingsUpdated implements DomainEvent {
    String deviceId;
    Settings settings;
}
