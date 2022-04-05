package devices.configuration.configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "features_configuration")
@AllArgsConstructor
@NoArgsConstructor
class FeaturesConfigurationEntity {

}
