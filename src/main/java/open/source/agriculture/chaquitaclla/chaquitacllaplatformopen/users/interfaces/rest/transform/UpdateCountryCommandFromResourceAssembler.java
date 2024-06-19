package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.UpdateCountryCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.UpdateCountryResource;

public class UpdateCountryCommandFromResourceAssembler {
    public static UpdateCountryCommand toCommandFromResource(Long countryId, UpdateCountryResource resource){
        return new UpdateCountryCommand(countryId,resource.name());
    }
}
