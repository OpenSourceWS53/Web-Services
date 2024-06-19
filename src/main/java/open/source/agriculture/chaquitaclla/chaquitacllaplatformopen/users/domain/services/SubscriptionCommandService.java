package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.Subscription;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.CreateSubscriptionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.DeleteSubscriptionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.UpdateSubscriptionCommand;

import java.util.Optional;

public interface SubscriptionCommandService {
    Long handle(CreateSubscriptionCommand command);
    Optional<Subscription> handle(UpdateSubscriptionCommand command);
    void handle(DeleteSubscriptionCommand command);
}

