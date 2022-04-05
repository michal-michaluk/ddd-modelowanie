package devices.configuration.remote.iot20;

import devices.configuration.remote.Protocols;
import lombok.Value;

@Value
class BootNotificationRequest {
    Device device;
    Reason reason;

    @Value
    static class Device {
        String serialNumber;
        String model;
        Modem modem;
        String vendorName;
        String firmwareVersion;
    }

    @Value
    static class Modem {
        String iccid;
        String imsi;
    }

    enum Reason {
        ApplicationReset,
        FirmwareUpdate,
        LocalReset,
        PowerUp,
        RemoteReset,
        ScheduledReset,
        Triggered,
        Unknown,
        Watchdog
    }

    devices.configuration.remote.intervals.Device toDevice(String deviceId, Protocols protocol) {
        return new devices.configuration.remote.intervals.Device(deviceId, device.getVendorName(), device.getModel(), protocol);
    }
}
