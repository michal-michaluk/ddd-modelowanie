package devices.configuration.remote;

import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class IntervalRulesTest {

    IntervalRules rules = IntervalRulesFixture.currentRules();

    @Test
    void matchInFirstDeviceIdRule() {
        Deviceish device = givenDevice().deviceId("EVB-P4562137").build();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(600);
    }

    @Test
    void matchInSecondDeviceIdRule() {
        Deviceish device = givenDevice().deviceId("t53_8264_019").build();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(2700);
    }

    @Test
    void matchInStrictModelRule() {
        Deviceish device = givenDevice()
                .vendor("ChargeStorm AB")
                .model("Chargestorm Connected")
                .build();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(60);
    }

    @Test
    void matchInRegexpModelRule() {
        Deviceish device = givenDevice()
                .vendor("EV-BOX")
                .model("G3-M5320E-F2-5321")
                .build();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(120);
    }

    @Test
    void matchInProtocolRule() {
        Deviceish device = givenDevice()
                .protocol(Protocols.IoT20)
                .build();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(600);
    }

    @Test
    void returnDefaultInterval() {
        Deviceish device = givenDevice().build();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(1800);
    }

    @NotNull
    private Deviceish.DeviceishBuilder givenDevice() {
        return Deviceish.builder()
                .deviceId("EVB-P4123437")
                .model("Garo")
                .vendor("CPF25 Family")
                .protocol(Protocols.IoT16);
    }
}
