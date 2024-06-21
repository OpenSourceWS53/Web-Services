package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User entity){
        return new UserResource(entity.getId(),entity.getName().firstName(),entity.getName().lastName(),entity.getEmail().emailUser(),entity.getPassword().password(),entity.getCity().getId(),entity.getSubscription().getId());
    }
}
