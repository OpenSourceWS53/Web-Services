package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.infrastructure.persistence.jpa.repositories;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates.Sowing;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.PhenologicalPhase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SowingRepository extends JpaRepository<Sowing, Long>{
    List<Sowing> findByPhenologicalPhase(PhenologicalPhase phase);

}
