package devices.configuration.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import devices.configuration.remote.IntervalRules;
import devices.configuration.remote.IntervalRulesRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IntervalRulesDocumentRepository implements IntervalRulesRepository {

    public static final String CONFIG_NAME = "IntervalRules";
    private final FeaturesConfigurationRepository repository;
    private final ObjectMapper mapper;

    public IntervalRules get() {
        return repository.findByName(CONFIG_NAME)
                .map(FeaturesConfigurationEntity::getConfiguration)
                .map(this::parse)
                .orElse(IntervalRules.defaultRules());
    }

    public String save(IntervalRules configuration) {
        return repository.findByName(CONFIG_NAME)
                .orElseGet(() -> repository.save(new FeaturesConfigurationEntity(CONFIG_NAME, "")))
                .withConfiguration(json(configuration))
                .getConfiguration();
    }

    @SneakyThrows
    private IntervalRules parse(String json) {
        return mapper.readValue(json, IntervalRules.class);
    }

    @SneakyThrows
    private String json(IntervalRules object) {
        return mapper.writeValueAsString(object);
    }
}
