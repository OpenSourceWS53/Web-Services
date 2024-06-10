package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        Long cityId = user.getCityId();
        Long subscriptionId = user.getSubscriptionId();

        return new UserResource(
                user.getId(),
                user.getName().getFirstName(),
                user.getName().getLastName(),
                user.getEmail().getEmailDirection(),
                user.getPassword().getPassword(),
                cityId != null ? cityId : 0L, // manejar null según tu lógica
                subscriptionId != null ? subscriptionId : 0L // manejar null según tu lógica
        );
    }
}
