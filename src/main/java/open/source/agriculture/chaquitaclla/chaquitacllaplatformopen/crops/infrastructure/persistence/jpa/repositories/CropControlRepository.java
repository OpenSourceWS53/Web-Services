package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.CropControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropControlRepository extends JpaRepository<CropControl, Long> {
    List<CropControl> findByCropId(Long id);
}