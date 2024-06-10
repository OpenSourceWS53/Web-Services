package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateDiseaseCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface DiseaseCommandService {
    Long handle(CreateDiseaseCommand command);
}
