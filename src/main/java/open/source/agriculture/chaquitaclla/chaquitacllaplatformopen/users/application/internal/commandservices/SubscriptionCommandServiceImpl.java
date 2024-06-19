package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.Subscription;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.CreateSubscriptionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.DeleteSubscriptionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.UpdateSubscriptionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.NameSubsCriptionRecord;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services.SubscriptionCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Long handle(CreateSubscriptionCommand command) {
        var subscription = new Subscription(command.name(), command.price(), command.description());
        try {
            subscriptionRepository.save(subscription);

        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating subscription: " + e.getMessage());
        }
        return subscription.getId();
    }

    @Override
    public Optional<Subscription> handle(UpdateSubscriptionCommand command) {
        var result = subscriptionRepository.findById(command.id());
        if (result.isEmpty())
            throw new IllegalArgumentException("Subscription with id " + command.id() + " not found");
        var subscriptionToUpdate = result.get();
        try {
            NameSubsCriptionRecord nameRecord=new NameSubsCriptionRecord(command.name());
            subscriptionToUpdate.setName(nameRecord);
            subscriptionToUpdate.setPrice(command.price());
            subscriptionToUpdate.setDescription(command.description());
            subscriptionRepository.save(subscriptionToUpdate);
            return Optional.of(subscriptionToUpdate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating subscription: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteSubscriptionCommand command) {
        var result = subscriptionRepository.findById(command.subscriptionId());
        if (result.isEmpty())
            throw new IllegalArgumentException("Subscription with id " + command.subscriptionId() + " not found");
        try {
            subscriptionRepository.delete(result.get());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting subscription: " + e.getMessage());
        }
    }
}
