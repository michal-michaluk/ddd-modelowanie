package devices.configuration.remote.intervals;

import devices.configuration.remote.Protocols;
import lombok.Builder;

@Builder
public record Device(
        String deviceId,
        String vendor,
        String model,
        Protocols protocol) {
}
