package devices.configuration.configs;

import devices.configuration.remote.IntervalRules;
import org.jetbrains.annotations.NotNull;

import static devices.configuration.JsonAssert.json;

public class FeaturesConfigurationFixture {

    @NotNull
    public static FeaturesConfigurationEntity entity(String name, String configuration) {
        return new FeaturesConfigurationEntity(name, configuration);
    }

    @NotNull
    public static FeaturesConfigurationEntity entity(IntervalRules configuration) {
        return new FeaturesConfigurationEntity(IntervalRulesDocumentRepository.CONFIG_NAME, json(configuration));
    }
}
