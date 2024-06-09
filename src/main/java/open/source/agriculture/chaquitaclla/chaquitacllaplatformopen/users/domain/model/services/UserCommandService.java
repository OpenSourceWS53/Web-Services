package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.CreateUserCommand;

public interface UserCommandService {
    Long handle(CreateUserCommand command);
}
