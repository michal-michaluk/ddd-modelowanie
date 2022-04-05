package devices.configuration.remote;

import lombok.Builder;

@Builder
public record Deviceish(
        String deviceId,
        String vendor,
        String model,
        Protocols protocol) {
}
