package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.*;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.Country;

import java.util.Optional;

public interface CountryCommandService {
  Long handle(CreateCountryCommand command);
  Optional<Country> handle(UpdateCountryCommand command);
  void handle(DeleteCountryCommand command);
}
