package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.CreateSowingControlCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.SowingControlResource;

public class CreateSowingControlCommandFromResourceAssembler {
    public static CreateSowingControlCommand fromResource(SowingControlResource resource) {
        return new CreateSowingControlCommand(
                resource.SowingId(),
                resource.sowingCondition(),
                resource.sowingStemCondition(),
                resource.sowingSoilMoisture()
        );
    }
}
