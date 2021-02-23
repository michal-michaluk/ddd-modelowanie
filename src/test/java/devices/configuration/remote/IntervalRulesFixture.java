package devices.configuration.remote;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class IntervalRulesFixture {

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
}
