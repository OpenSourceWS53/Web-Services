package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources;

public record CreateUserResource(String firstName,String lastName,String email,String password,Long cityId,Long subscriptionId) {
}
