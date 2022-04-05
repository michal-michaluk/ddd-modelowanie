package devices.configuration.device;

import lombok.Value;

import javax.validation.Valid;

@Value
public class UpdateDevice {
    @Valid
    Location location;
    @Valid
    OpeningHours openingHours;
    @Valid
    Settings settings;
    @Valid
    Ownership ownership;

    public void apply(Device device) {
        if (location != null) {
            device.updateLocation(location);
        }
        if (openingHours != null) {
            device.updateOpeningHours(openingHours);
        }
        if (settings != null) {
            device.updateSettings(settings);
        }
        if (ownership != null) {
            device.assignTo(ownership);
        }
    }
}
