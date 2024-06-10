package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetUserByEmailQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.services.UserCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.services.UserQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.infrastructure.persistence.jpa.repositories.UserRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.CreateUserResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.UserResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name="/api/v1/users",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "User", description = "User API")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserRepository userRepository;

    public UserController(UserCommandService userCommandService, UserRepository userRepository) {
        this.userCommandService = userCommandService;
        this.userRepository = userRepository;
    }
    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource createUserResource) {
        var command = CreateUserCommandFromResourceAssembler.toCommandFromResource(createUserResource);
        var userId = userCommandService.handle(command);
        var user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user);
        return ResponseEntity.ok(userResource);
    }

}
