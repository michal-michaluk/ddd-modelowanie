package devices.configuration.device;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Device {
    private final String deviceId;
    private OpeningHours openingHours;
    private Settings settings;

    public void updateOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public void updateSettings(Settings settings) {
        this.settings = this.settings.merge(settings);
    }
}

