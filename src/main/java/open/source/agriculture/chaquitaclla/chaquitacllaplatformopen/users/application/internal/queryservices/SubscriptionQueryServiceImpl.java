package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.application.internal.queryservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.Subscription;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetAllSubscriptionQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetSubscriptionByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services.SubscriptionQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Optional<Subscription> handle(GetSubscriptionByIdQuery query) {
        return subscriptionRepository.findById(query.subscriptionId());
    }

    @Override
    public List<Subscription> handle(GetAllSubscriptionQuery query) {
        return subscriptionRepository.findAll();
    }
}
