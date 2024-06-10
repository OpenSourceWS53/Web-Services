package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreatePestCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.PestCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.PestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PestCommandServiceImpl implements PestCommandService {

    private final PestRepository pestRepository;

    @Autowired
    public PestCommandServiceImpl(PestRepository pestRepository) {
        this.pestRepository = pestRepository;
    }

    @Override
    public Long handle(CreatePestCommand command) {
        Pest pest = new Pest(command.name(), command.description(), command.solution(), command.cropId());
        pest = pestRepository.save(pest);
        return pest.getId();
    }
}
