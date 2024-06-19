package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.CreateCountryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.DeleteCountryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.UpdateCountryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.Country;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services.CountryCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.infrastructure.persistence.jpa.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryCommandServiceImpl implements CountryCommandService {
    private final CountryRepository countryRepository;

    public CountryCommandServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Long handle(CreateCountryCommand command) {
        var country = new Country(command.name());
        try{
            countryRepository.save(country);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating country: " + e.getMessage());
        }
        return country.getId();
    }

    @Override
    public Optional<Country> handle(UpdateCountryCommand command) {
        var result = countryRepository.findById(command.id());
        if (result.isEmpty())
            throw new IllegalArgumentException("Country with id " + command.id() + " not found");
        var countryToUpdate = result.get();
        try {
            countryToUpdate.setName(command.name());
            countryRepository.save(countryToUpdate);
            return Optional.of(countryToUpdate);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while updating country: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteCountryCommand command) {
      if(!countryRepository.existsById(command.countryId())){
          throw new IllegalArgumentException("Country does not exist");
      }
      try {
          countryRepository.deleteById(command.countryId());
      } catch (Exception e) {
          throw new IllegalArgumentException("Error while deleting country: " + e.getMessage());
      }
    }
}
