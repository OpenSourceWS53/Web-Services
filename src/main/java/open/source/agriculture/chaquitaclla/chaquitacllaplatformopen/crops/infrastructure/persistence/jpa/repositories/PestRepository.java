package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PestRepository extends JpaRepository <Pest, Integer> {
}
