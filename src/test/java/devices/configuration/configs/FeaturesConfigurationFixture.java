package devices.configuration.configs;

import devices.configuration.remote.IntervalRules;
import org.jetbrains.annotations.NotNull;

public class FeaturesConfigurationFixture {

    @NotNull
    public static FeaturesConfigurationEntity entity(String name, String configuration) {
        return new FeaturesConfigurationEntity();
    }

    @NotNull
    public static FeaturesConfigurationEntity entity(IntervalRules configuration) {
        return new FeaturesConfigurationEntity();
    }
}
