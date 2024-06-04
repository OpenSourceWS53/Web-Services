package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreatePestCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.PestResource;

public class CreatePestSourceCommandFromResourceAssembler {
    public CreatePestCommand toCommand (PestResource resource){
        return new CreatePestCommand(
                resource.name(),
                resource.description(),
                resource.solution(),
                resource.cropId()
        );
    }
}
