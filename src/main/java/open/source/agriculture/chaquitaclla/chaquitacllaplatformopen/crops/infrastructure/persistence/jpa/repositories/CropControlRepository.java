package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.CropControl;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Care;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropControlRepository extends JpaRepository<CropControl, Long> {
    List<CropControl> findByCropId(Long id);
    List<CropControl> findByCare(Care care);
    List<CropControl> findByDisease(Disease disease);
    List<CropControl> findByPest(Pest pest);
}