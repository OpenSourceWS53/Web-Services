package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.UpdateSubscriptionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.UpdateSubscriptionResource;

public class UpdateSubscriptionCommandFromResourceAssembler {
    public static UpdateSubscriptionCommand toCommandFromResource(Long subscriptionId, UpdateSubscriptionResource resource){
        return new UpdateSubscriptionCommand(subscriptionId,resource.name(),resource.price(),resource.description());
    }
}
