package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.CreateCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.DeleteCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.UpdateCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.City;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.Country;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services.CityCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.infrastructure.persistence.jpa.repositories.CityRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.infrastructure.persistence.jpa.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityCommandServiceImpl implements CityCommandService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public CityCommandServiceImpl(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Long handle(CreateCityCommand command) {
        Country country = countryRepository.findById(command.countryId())
                .orElseThrow(() -> new IllegalArgumentException("Country with id " + command.countryId() + " not found"));
        var city = new City(command.name(), country);
        cityRepository.save(city);
        return city.getId();
    }

    @Override
    public Optional<City> handle(UpdateCityCommand command) {
        var result= cityRepository.findById(command.id());
        if (result.isEmpty())
            throw new IllegalArgumentException("City with id " + command.id() + " not found");
        var cityToUpdate = result.get();
        Country country = countryRepository.findById(command.countryId())
                .orElseThrow(() -> new IllegalArgumentException("Country with id " + command.countryId() + " not found"));
        try{
                 cityToUpdate.setName(command.name());
                 cityToUpdate.setCountry(country);
                 cityRepository.save(cityToUpdate);
                 return Optional.of(cityToUpdate);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Error while updating city: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteCityCommand command) {
        if (!cityRepository.existsById(command.cityId())) {
            throw new IllegalArgumentException("City does not exist");
        }
        try {
            cityRepository.deleteById(command.cityId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting city: " + e.getMessage());
        }

    }
}
