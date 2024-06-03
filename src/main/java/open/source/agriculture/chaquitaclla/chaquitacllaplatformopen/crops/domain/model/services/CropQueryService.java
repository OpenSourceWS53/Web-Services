package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Care;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllCropsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetCaresByCropIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetCropByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CropQueryService{
    Optional<Crop> handle(GetCropByIdQuery query);
    List<Crop> handle(GetAllCropsQuery query);
    Optional<Care> handle(GetCaresByCropIdQuery query);
}
