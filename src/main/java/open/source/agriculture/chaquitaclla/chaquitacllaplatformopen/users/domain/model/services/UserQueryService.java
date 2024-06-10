package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetUserByEmailAndPasswordQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetUserByEmailQuery;

import java.util.Optional;

public interface UserQueryService {
    Optional<User>  handle(GetUserByEmailAndPasswordQuery query);
    Optional<User> handle(GetUserByEmailQuery query);
}
