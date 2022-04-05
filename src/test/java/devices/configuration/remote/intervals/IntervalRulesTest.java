package devices.configuration.remote.intervals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class IntervalRulesTest {

    IntervalRules rules = IntervalRulesFixture.currentRules();

    @Test
    void matchInFirstDeviceIdRule() {
        Device device = IntervalRulesFixture.matchingDeviceIdRule1();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(600);
    }

    @Test
    void matchInSecondDeviceIdRule() {
        Device device = IntervalRulesFixture.matchingDeviceIdRule2();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(2700);
    }

    @Test
    void matchInStrictModelRule() {
        Device device = IntervalRulesFixture.matchingStrictModelRule();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(60);
    }

    @Test
    void matchInRegexpModelRule() {
        Device device = IntervalRulesFixture.matchingRegexModelRule();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(120);
    }

    @Test
    void matchInProtocolRule() {
        Device device = IntervalRulesFixture.matchingProtocol20Rule();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(600);
    }

    @Test
    void returnDefaultInterval() {
        Device device = IntervalRulesFixture.notMatchingAnyRule();

        Duration interval = rules.calculateInterval(device);

        Assertions.assertThat(interval).hasSeconds(1800);
    }
}
