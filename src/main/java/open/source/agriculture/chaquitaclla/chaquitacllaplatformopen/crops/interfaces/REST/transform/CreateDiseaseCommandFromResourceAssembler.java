package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CreateDiseaseResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateDiseaseCommand;

public class CreateDiseaseCommandFromResourceAssembler {

    public static CreateDiseaseCommand toCommandFromResource(CreateDiseaseResource resource) {
        return new CreateDiseaseCommand(
                resource.name(),
                resource.description(),
                resource.solution(),
                resource.cropId()
        );
    }
}