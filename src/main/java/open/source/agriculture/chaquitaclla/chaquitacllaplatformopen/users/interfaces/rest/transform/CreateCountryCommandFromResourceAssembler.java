package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.CreateCountryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.CreateCountryResource;

public class CreateCountryCommandFromResourceAssembler {
    public static CreateCountryCommand toCommandFromResource(CreateCountryResource resource){
        return new CreateCountryCommand(resource.name());
    }
}
