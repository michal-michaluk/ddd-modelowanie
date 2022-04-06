package devices.configuration.device.event.sourcing;

import devices.configuration.DomainEvent;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public class LastEvents {
    Map<? extends Class<? extends DomainEvent>, ? extends DomainEvent> last;

    public static LastEvents fromHistoryOf(List<DomainEvent> events) {
        return new LastEvents(events.stream()
                .collect(Collectors.toMap(
                        DomainEvent::getClass,
                        Function.identity(),
                        (last, previous) -> last
                )));
    }

    public <R, T extends DomainEvent> R getOrNull(Class<T> type, Function<T, R> fun) {
        return Optional.ofNullable(last.get(type))
                .map(type::cast)
                .map(fun)
                .orElse(null);
    }

    public <R, T extends DomainEvent> R getOrDefault(Class<T> type, Function<T, R> fun, R defaultValue) {
        return Optional.ofNullable(last.get(type))
                .map(type::cast)
                .map(fun)
                .orElse(defaultValue);
    }
}
