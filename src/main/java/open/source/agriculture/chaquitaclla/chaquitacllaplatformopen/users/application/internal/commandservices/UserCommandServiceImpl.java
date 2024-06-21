package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.Subscription;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.CreateUserCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.DeleteUserCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.UpdateUserCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.City;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.EmailUserRecord;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.NameUserRecord;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.Password;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services.UserCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.infrastructure.persistence.jpa.repositories.CityRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final SubscriptionRepository subscriptionRepository;

    public UserCommandServiceImpl(UserRepository userRepository, CityRepository cityRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Long handle(CreateUserCommand command) {
        City city = cityRepository.findById(command.cityId())
                .orElseThrow(() -> new IllegalArgumentException("City with id " + command.cityId() + " not found"));
        Subscription subscription = subscriptionRepository.findById(command.subscriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription with id " + command.subscriptionId() + " not found"));
        var user = new User(command.firstName(),command.lastName(), command.email(), command.password(), city, subscription);
        Optional<User> existingUser = userRepository.findByEmail(command.email());//Esta hace qeu busca el email si existe mandad error, pero al parecer necesita una tta uno tipo email y no string eliminarlo y ver que pasa 
        if (existingUser.isPresent()) {//Indica que Email no reconoce por alguna razon
            throw new IllegalArgumentException("Email " + command.email() + " is already in use");
        }
        try {

            userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating user: " + e.getMessage());
        }

        return user.getId();
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        var result= userRepository.findById(command.userId());
        if (result.isEmpty())
            throw new IllegalArgumentException("User with id " + command.userId() + " not found");
        var userToUpdate = result.get();
        City city = cityRepository.findById(command.cityId())
                .orElseThrow(() -> new IllegalArgumentException("City with id " + command.cityId() + " not found"));
        Subscription subscription = subscriptionRepository.findById(command.subscriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription with id " + command.subscriptionId() + " not found"));
        try{
            NameUserRecord nameRecord=new NameUserRecord(command.firstName(),command.lastName());
            EmailUserRecord emailDirection=new EmailUserRecord(command.email());
            Password password=new Password(command.password());
            userToUpdate.setName(nameRecord);
            userToUpdate.setEmail(emailDirection);
            userToUpdate.setPassword(password);
            userToUpdate.setCity(city);
            userToUpdate.setSubscription(subscription);
            userRepository.save(userToUpdate);
            return Optional.of(userToUpdate);
        }
        catch (Exception e) {
            throw new IllegalArgumentException("Error while updating user: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteUserCommand command) {
        if (!userRepository.existsById(command.userId())) {
            throw new IllegalArgumentException("User does not exist");
        }
        try {
            userRepository.deleteById(command.userId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting user: " + e.getMessage());
        }

    }
}
