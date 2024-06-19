package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.City;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetAllCitysQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetCityByIdQuery;

import java.util.List;
import java.util.Optional;

public interface CityQueryService {
    Optional<City> handle(GetCityByIdQuery query);
    List<City> handle(GetAllCitysQuery query);
}
