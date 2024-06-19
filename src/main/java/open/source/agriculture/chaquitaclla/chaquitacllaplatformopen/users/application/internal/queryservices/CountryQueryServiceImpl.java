package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.application.internal.queryservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.Country;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetAllCountrysQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetCountryByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services.CountryQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.infrastructure.persistence.jpa.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryQueryServiceImpl implements CountryQueryService {
    private final CountryRepository countryRepository;

    public CountryQueryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> handle(GetCountryByIdQuery query) {
        return countryRepository.findById(query.countryId());
    }

    @Override
    public List<Country> handle(GetAllCountrysQuery query) {
        return countryRepository.findAll();
    }
}
