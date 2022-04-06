package devices.configuration.device.persistence;

import devices.configuration.device.Device;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "device_document")
@NoArgsConstructor
class DeviceDocumentEntity {
    @Id
    private String deviceId;
    @Version
    private long version;

    @Setter
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Device device;

}
