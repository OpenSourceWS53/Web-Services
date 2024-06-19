package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.Subscription;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetAllSubscriptionQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetSubscriptionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SubscriptionQueryService {
    Optional<Subscription> handle(GetSubscriptionByIdQuery query);
    List<Subscription> handle(GetAllSubscriptionQuery query);
}
