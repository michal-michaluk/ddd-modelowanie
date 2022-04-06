package devices.configuration;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import devices.configuration.device.events.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = LegacyEvents.OwnershipUpdatedV1.class, name = "OwnershipUpdated_v1"),
        @JsonSubTypes.Type(value = OwnershipUpdated.class, name = "OwnershipUpdated_v2"),
        @JsonSubTypes.Type(value = OpeningHoursUpdated.class, name = "OpeningHoursUpdated_v1"),
        @JsonSubTypes.Type(value = LocationUpdated.class, name = "LocationUpdated_v1"),
        @JsonSubTypes.Type(value = SettingsUpdated.class, name = "SettingsUpdated_v1"),
})
public interface DomainEvent {
}
