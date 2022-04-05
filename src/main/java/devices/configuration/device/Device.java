package devices.configuration.device;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Device {
    final String deviceId;
    private Ownership ownership;
    private Location location;
    private OpeningHours openingHours;
    private Settings settings;

    public void assignTo(Ownership ownership) {
        this.ownership = ownership;
    }

    public void updateLocation(Location location) {
        this.location = location;
    }

    public void updateOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public void updateSettings(Settings settings) {
        this.settings = this.settings.merge(settings);
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
}