package devices.configuration.remote.intervals;

import devices.configuration.remote.Protocols;
import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class IntervalRulesFixture {

    private static final String T_53_8264_019 = "t53_8264_019";
    private static final String EVB_P_4562137 = "EVB-P4562137";

    public static IntervalRules currentRules() {
        return new IntervalRules(
                List.of(
                        IntervalRules.byDeviceIdRule(
                                Duration.ofSeconds(600),
                                Set.of("EVB-P4562137", "ALF-9571445", "CS_7155_CGC100", "EVB-P9287312", "ALF-2844179")
                        ),
                        IntervalRules.byDeviceIdRule(
                                Duration.ofSeconds(2700),
                                Set.of("t53_8264_019", "EVB-P15079256", "EVB-P0984003", "EVB-P1515640", "EVB-P1515526")
                        )),
                List.of(
                        IntervalRules.byModelRule(
                                Duration.ofSeconds(60),
                                "Alfen BV",
                                Pattern.compile("NG920-5250[6-9]")
                        ),
                        IntervalRules.byModelRule(
                                Duration.ofSeconds(60),
                                "ChargeStorm AB",
                                Pattern.compile("Chargestorm Connected")
                        ),
                        IntervalRules.byModelRule(
                                Duration.ofSeconds(120),
                                "EV-BOX",
                                Pattern.compile("G3-M5320E-F2.*")
                        )),
                List.of(IntervalRules.byProtocolRule(Duration.ofSeconds(600), Protocols.IoT20)),
                Duration.ofSeconds(1800)
        );
    }

    @NotNull
    public static TestDevice.TestDeviceBuilder givenDevice() {
        return TestDevice.builder()
                .deviceId("EVB-P4123437")
                .model("Garo")
                .vendor("CPF25 Family")
                .protocol(Protocols.IoT16);
    }

    public static Device notMatchingAnyRule() {
        return givenDevice().build().toDevice();
    }

    public static Device matchingDeviceIdRule1() {
        return givenDevice()
                .deviceId(EVB_P_4562137)
                .build().toDevice();
    }

    public static Device matchingDeviceIdRule2() {
        return givenDevice()
                .deviceId(T_53_8264_019)
                .build().toDevice();
    }

    public static Device matchingStrictModelRule() {
        return IntervalRulesFixture.givenDevice()
                .vendor("ChargeStorm AB")
                .model("Chargestorm Connected")
                .build().toDevice();
    }

    public static Device matchingRegexModelRule() {
        return IntervalRulesFixture.givenDevice()
                .vendor("EV-BOX")
                .model("G3-M5320E-F2-5321")
                .build().toDevice();
    }

    public static Device matchingProtocol20Rule() {
        return IntervalRulesFixture.givenDevice()
                .protocol(Protocols.IoT20)
                .build().toDevice();
    }

    @Builder
    private static class TestDevice {
        String deviceId;
        String vendor;
        String model;
        Protocols protocol;

        Device toDevice() {
            return new Device(
                    deviceId,
                    vendor,
                    model,
                    protocol
            );
        }
    }
}
