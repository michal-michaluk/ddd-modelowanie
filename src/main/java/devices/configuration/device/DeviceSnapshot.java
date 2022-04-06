package devices.configuration.device;

public record DeviceSnapshot(
        String deviceId,
        Ownership ownership,
        Location location,
        OpeningHours openingHours,
        Settings settings,
        Violations violations,
        Visibility visibility) {
}
