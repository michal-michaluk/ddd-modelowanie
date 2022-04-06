package devices.configuration.device;

import devices.configuration.DomainEvent;
import devices.configuration.device.events.LocationUpdated;
import devices.configuration.device.events.OpeningHoursUpdated;
import devices.configuration.device.events.OwnershipUpdated;
import devices.configuration.device.events.SettingsUpdated;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class Device {
    final String deviceId;
    final List<DomainEvent> events;
    private Ownership ownership;
    private Location location;
    private OpeningHours openingHours;
    private Settings settings;

    public void assignTo(Ownership ownership) {
        if (!Objects.equals(this.ownership, ownership)) {
            this.ownership = ownership;
            events.add(new OwnershipUpdated(deviceId, ownership));
        }
    }

    public void updateOpeningHours(OpeningHours openingHours) {
        if (!Objects.equals(this.openingHours, openingHours)) {
            this.openingHours = openingHours;
            events.add(new OpeningHoursUpdated(deviceId, openingHours));
        }
    }

    public void updateLocation(Location location) {
        if (!Objects.equals(this.location, location)) {
            this.location = location;
            events.add(new LocationUpdated(deviceId, location));
        }
    }

    public void updateSettings(Settings settings) {
        Settings merged = this.settings.merge(settings);
        if (!Objects.equals(this.settings, merged)) {
            this.settings = merged;
            events.add(new SettingsUpdated(deviceId, this.settings));
        }
    }

    private Violations checkViolations() {
        return Violations.builder()
                .operatorNotAssigned(ownership == null || ownership.operator() == null)
                .providerNotAssigned(ownership == null || ownership.provider() == null)
                .locationMissing(location == null)
                .showOnMapButMissingLocation(settings.isShowOnMap() && location == null)
                .showOnMapButNoPublicAccess(settings.isShowOnMap() && !settings.isPublicAccess())
                .build();
    }

    public DeviceSnapshot toSnapshot() {
        Violations violations = checkViolations();
        Visibility visibility = Visibility.basedOn(
                violations.isValid() && settings.isPublicAccess(),
                settings.isShowOnMap()
        );
        return new DeviceSnapshot(
                deviceId,
                ownership,
                location,
                openingHours,
                settings,
                violations,
                visibility
        );
    }
}
