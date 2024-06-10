package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(
                user.getId(),
                user.getName().getFirstName(),
                user.getName().getLastName(),
                user.getEmail().getEmailDirection(),
                user.getPassword().getPassword(),
                user.getCityId(),
                user.getSubscriptionId()
        );
    }
}
