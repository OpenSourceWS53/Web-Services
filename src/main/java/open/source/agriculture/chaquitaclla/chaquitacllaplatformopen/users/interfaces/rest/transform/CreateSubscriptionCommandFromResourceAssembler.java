package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.CreateSubscriptionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.CreateSubscriptionResource;

public class CreateSubscriptionCommandFromResourceAssembler {
    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource){
        return new CreateSubscriptionCommand(resource.name(), resource.price(), resource.description());
    }
}
