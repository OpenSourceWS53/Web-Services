package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.CreateCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.CreateCityResource;

public class CreateCityCommandFromResourceAssembler {
    public static CreateCityCommand toCommandFromResource(CreateCityResource resource){
        return new CreateCityCommand(resource.name(),resource.countryId());
    }
}
