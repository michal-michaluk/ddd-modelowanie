package devices.configuration.remote;

import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public record IntervalRules(
        List<DeviceIdRule> byIds,
        List<ModelRule> byModel,
        List<ProtocolRule> byProtocol,
        Duration def) {

    static DeviceIdRule byDeviceIdRule(Duration interval, Set<String> deviceId) {
        return new DeviceIdRule(interval, deviceId);
    }

    static ModelRule byModelRule(Duration interval, String vendor, Pattern model) {
        return new ModelRule(interval, vendor, model);
    }

    static ProtocolRule byProtocolRule(Duration interval, Protocols protocol) {
        return new ProtocolRule(interval, protocol);
    }

    public Duration calculateInterval(Deviceish device) {
        return Stream.of(byIds, byModel, byProtocol)
                .flatMap(Collection::stream)
                .filter(rule -> rule.matches(device))
                .findFirst()
                .map(Rule::interval)
                .orElse(def);
    }

    interface Rule {
        boolean matches(Deviceish device);

        Duration interval();
    }

    record DeviceIdRule(Duration interval, Set<String> devices) implements Rule {
        @Override
        public boolean matches(Deviceish device) {
            return devices.contains(device.deviceId());
        }
    }

    record ModelRule(Duration interval, String vendor, Pattern model) implements Rule {
        @Override
        public boolean matches(Deviceish device) {
            return Objects.equals(vendor, device.vendor())
                    && model.matcher(device.model()).matches();
        }
    }

    record ProtocolRule(Duration interval, Protocols protocol) implements Rule {
        @Override
        public boolean matches(Deviceish device) {
            return Objects.equals(protocol, device.protocol());
        }
    }
}