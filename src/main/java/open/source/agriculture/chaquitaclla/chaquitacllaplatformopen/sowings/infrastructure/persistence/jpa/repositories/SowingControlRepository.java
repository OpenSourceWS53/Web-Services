package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.infrastructure.persistence.jpa.repositories;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates.Sowing;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.entities.SowingControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SowingControlRepository extends JpaRepository<SowingControl, Long>{
    List<SowingControl> findBySowingId(Long aLong);
}
