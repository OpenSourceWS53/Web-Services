package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllPestsQuery;

import java.util.List;
import java.util.Optional;

public interface PestQueryService {
    List<Pest> handle(GetAllPestsQuery query);

    Optional<Pest> findPestById(Long pestId);
}
