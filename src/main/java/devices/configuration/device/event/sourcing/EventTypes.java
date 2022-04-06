package devices.configuration.device.event.sourcing;

import devices.configuration.DomainEvent;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class EventTypes {

    private static Map<Class<?>, Type> mapping;

    public static Type of(DomainEvent event) {
        return of(event.getClass());
    }

    public static Type of(Class<? extends DomainEvent> type) {
        return mapping.get(type);
    }

    public static boolean hasTypeName(Class<? extends DomainEvent> type, String typeName) {
        return EventTypes.of(type).getType().equals(typeName);
    }

    @Value
    public static class Type {
        String type;
        String version;

        public static Type of(String typeName) {
            String[] parts = typeName.split("_v");
            if (parts.length != 2 || StringUtils.isBlank(parts[1])) {
                throw new IllegalArgumentException(
                        "Version required in " + DomainEvent.class.getName() + " JsonSubTypes name, like StationProtocolChanged_v1, '_v' part is important, thrown for type name: " + typeName
                );
            }
            return new Type(parts[0], parts[1]);
        }
    }

    public static void init(Map<Class<?>, Type> mapping) {
        EventTypes.mapping = mapping;
    }
}
