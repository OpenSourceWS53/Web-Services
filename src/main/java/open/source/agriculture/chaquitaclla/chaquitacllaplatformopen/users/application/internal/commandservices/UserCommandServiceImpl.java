package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.CreateUserCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.services.UserCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.EmailDirection;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserCommandServiceImpl implements UserCommandService{
    private UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public Long handle(CreateUserCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("User with email already exists");
        }
        var email = new EmailDirection(command.email());
        var user = new User(command.firstName(), command.lastName(), email, command.password());
        userRepository.save(user);
        return user.getId();
    }
}
