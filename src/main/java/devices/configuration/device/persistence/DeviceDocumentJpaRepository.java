package devices.configuration.device.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceDocumentJpaRepository
        extends CrudRepository<DeviceDocumentEntity, String> {
    Optional<DeviceDocumentEntity> findByDeviceId(String deviceId);

    Page<DeviceDocumentEntity> findAll(Pageable pageable);
}
