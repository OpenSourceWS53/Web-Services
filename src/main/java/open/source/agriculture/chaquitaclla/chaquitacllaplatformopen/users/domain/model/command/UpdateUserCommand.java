package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command;

public record UpdateUserCommand(Long userId,String firstName,String lastName,String email,String password,Long cityId,Long subscriptionId) {
}
