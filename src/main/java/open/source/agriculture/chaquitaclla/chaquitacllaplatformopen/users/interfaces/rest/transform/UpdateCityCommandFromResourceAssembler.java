package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.UpdateCityCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.UpdateCityResource;

public class UpdateCityCommandFromResourceAssembler {
    public static UpdateCityCommand toCommandFromResource(Long cityId, UpdateCityResource resource){
        return new UpdateCityCommand(cityId,resource.name(),resource.countryId());
    }
}
