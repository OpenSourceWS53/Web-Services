package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllDiseasesQuery;

import java.util.List;
import java.util.Optional;

public interface DiseaseQueryService {
    List<Disease> handle(GetAllDiseasesQuery query);

    Optional<Disease> findById(Long diseaseId);
}
