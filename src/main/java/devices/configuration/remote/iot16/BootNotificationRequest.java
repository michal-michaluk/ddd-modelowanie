package devices.configuration.remote.iot16;

import devices.configuration.remote.Protocols;
import devices.configuration.remote.intervals.Device;
import lombok.Value;

@Value
class BootNotificationRequest {
    String chargePointVendor;
    String chargePointModel;
    String chargePointSerialNumber;
    String chargeBoxSerialNumber;
    String firmwareVersion;
    String iccid;
    String imsi;
    String meterType;
    String meterSerialNumber;

    Device toDevice(String deviceId, Protocols protocol) {
        return new Device(deviceId, chargePointVendor, chargePointModel, protocol);
    }
}
