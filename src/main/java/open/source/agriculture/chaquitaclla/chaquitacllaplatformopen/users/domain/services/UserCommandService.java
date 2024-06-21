package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.CreateUserCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.DeleteUserCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.UpdateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Long handle(CreateUserCommand command);
    Optional<User> handle(UpdateUserCommand command);
    void handle(DeleteUserCommand command);
}
