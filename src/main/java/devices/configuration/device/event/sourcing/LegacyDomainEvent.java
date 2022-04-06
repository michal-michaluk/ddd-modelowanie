package devices.configuration.device.event.sourcing;

import devices.configuration.DomainEvent;

public interface LegacyDomainEvent {

    static DomainEvent normalise(DomainEvent event) {
        if (event instanceof LegacyDomainEvent) {
            return ((LegacyDomainEvent) event).normalise();
        } else {
            return event;
        }
    }

    DomainEvent normalise();
}
