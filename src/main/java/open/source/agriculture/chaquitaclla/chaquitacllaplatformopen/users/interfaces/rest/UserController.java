package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.DeleteUserCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.command.UpdateUserCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetAllUserQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetUserByEmailAndPasswordQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.queries.GetUserByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services.UserCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.services.UserQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.CreateUserResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.UpdateUserResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.UserResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController
@RequestMapping(value="/api/v1/user",produces = APPLICATION_JSON_VALUE)
@Tag(name = "User", description = "User Management Endpoints")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }
    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource createUserResource){
        var createUserCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(createUserResource);
        var userId = userCommandService.handle(createUserCommand);
        if (userId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty())
            return ResponseEntity.badRequest().build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }
    @GetMapping("/{userEmail}/{userPassword}")
    public ResponseEntity<UserResource> getUserByEmailAndPassword(@PathVariable String userEmail, @PathVariable String userPassword) {
        var getUserByEmailAndPasswordQuery = new GetUserByEmailAndPasswordQuery(userEmail, userPassword);
        var user = userQueryService.handle(getUserByEmailAndPasswordQuery);
        if (user.isEmpty())
            return ResponseEntity.badRequest().build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUser(){
        var getAllUserQuery = new GetAllUserQuery();
        var user = userQueryService.handle(getAllUserQuery);
        var userResources = user.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(userResources);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource updateUserResource) {
        var updateUserCommand = UpdateUserCommandFromResourceAssembler.toCommandFromResource(userId, updateUserResource);
        var updatedUser = userCommandService.handle(updateUserCommand);
        if(updatedUser.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var cityResource = UserResourceFromEntityAssembler.toResourceFromEntity(updatedUser.get());
        return ResponseEntity.ok(cityResource);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        var deleteUserCommand = new DeleteUserCommand(userId);
        userCommandService.handle(deleteUserCommand);
        return ResponseEntity.ok("User with given id successfully deleted");
    }
}
