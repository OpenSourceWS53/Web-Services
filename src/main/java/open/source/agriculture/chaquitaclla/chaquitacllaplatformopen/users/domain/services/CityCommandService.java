package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.CreateCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.DeleteCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.UpdateCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.City;

import java.util.Optional;

public interface CityCommandService {
    Long handle(CreateCityCommand command);
    Optional<City> handle(UpdateCityCommand command);
    void handle(DeleteCityCommand command);
}
