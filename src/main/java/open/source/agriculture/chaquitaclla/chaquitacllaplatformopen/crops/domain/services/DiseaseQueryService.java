package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllDiseasesQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetDiseasesByCropIdQuery;

import java.util.List;
import java.util.Optional;

public interface DiseaseQueryService {
    List<Disease> handle(GetAllDiseasesQuery query);
    List<Disease> handle(GetDiseasesByCropIdQuery query);
    Optional<Disease> findById(Long diseaseId);
}
