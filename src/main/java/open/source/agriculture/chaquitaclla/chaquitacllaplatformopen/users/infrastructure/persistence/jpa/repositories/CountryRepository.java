package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.infrastructure.persistence.jpa.repositories;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {
   Optional<Country> findById(Long id);
}
