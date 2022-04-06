package devices.configuration.device.events;

import devices.configuration.DomainEvent;
import devices.configuration.device.Ownership;
import devices.configuration.device.event.sourcing.LegacyDomainEvent;
import lombok.Value;

public class LegacyEvents {

    @Value
    public static class OwnershipUpdatedV1 implements LegacyDomainEvent {
        String deviceId;
        String operator;
        String provider;

        @Override
        public DomainEvent normalise() {
            return new OwnershipUpdated(deviceId,
                    new Ownership(operator, provider)
            );
        }
    }

}
