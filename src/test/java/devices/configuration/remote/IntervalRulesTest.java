package devices.configuration.remote;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class IntervalRulesTest {

    IntervalRules rules = IntervalRulesFixture.currentRules();

    @Test
    void matchInFirstDeviceIdRule() {
        Deviceish device = IntervalRulesFixture.matchingDeviceIdRule1();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(600);
    }

    @Test
    void matchInSecondDeviceIdRule() {
        Deviceish device = IntervalRulesFixture.matchingDeviceIdRule2();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(2700);
    }

    @Test
    void matchInStrictModelRule() {
        Deviceish device = IntervalRulesFixture.matchingStrictModelRule();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(60);
    }

    @Test
    void matchInRegexpModelRule() {
        Deviceish device = IntervalRulesFixture.matchingRegexModelRule();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(120);
    }

    @Test
    void matchInProtocolRule() {
        Deviceish device = IntervalRulesFixture.matchingProtocol20Rule();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(600);
    }

    @Test
    void returnDefaultInterval() {
        Deviceish device = IntervalRulesFixture.notMatchingAnyRule();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(1800);
    }
}
