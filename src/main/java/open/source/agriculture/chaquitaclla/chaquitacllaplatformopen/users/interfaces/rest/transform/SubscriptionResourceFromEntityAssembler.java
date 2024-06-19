package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.Subscription;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.SubscriptionResource;

public class SubscriptionResourceFromEntityAssembler {
    public static SubscriptionResource toResourceFromEntity(Subscription entity){
        return new SubscriptionResource(entity.getId(),entity.getName().subscriptionName(),entity.getPrice(),entity.getDescription());
    }
}
