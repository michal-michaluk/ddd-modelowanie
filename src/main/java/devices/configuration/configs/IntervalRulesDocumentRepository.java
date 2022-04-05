package devices.configuration.configs;

import devices.configuration.remote.IntervalRules;
import devices.configuration.remote.IntervalRulesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IntervalRulesDocumentRepository implements IntervalRulesRepository {

    private final FeaturesConfigurationRepository repository;

    public IntervalRules get() {
        return null;
    }

    public String save(IntervalRules configuration) {
        return null;
    }
}
