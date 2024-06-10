package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.EmailDirection;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.NameUserRecord;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.Password;

public record UserResource(Long id, String firstName,
                           String lastName,
                           String email,
                           String password,
                           Long cityId,
                           Long subscriptionId) {

}
