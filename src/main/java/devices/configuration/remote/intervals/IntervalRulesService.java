package devices.configuration.remote.intervals;

public class IntervalRulesService {
    IntervalRulesRepository repository;

    public int calculateIntervalAsSeconds(Device device) {
        return (int) repository.get().calculateInterval(device).toSeconds();
    }
}
