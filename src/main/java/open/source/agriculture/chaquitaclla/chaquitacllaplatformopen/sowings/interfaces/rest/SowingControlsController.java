package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.DeleteSowingControlBySowingIdCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetSowingControlByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetSowingControlsBySowingIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingControlCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingControlQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.CreateSowingControlResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.SowingControlResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform.CreateSowingControlCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform.SowingControlResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/sowings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sowings", description = "Sowing Management Endpoints")
public class SowingControlsController {
    private final SowingControlCommandService sowingControlCommandService;
    private final SowingControlQueryService sowingControlQueryService;

    public SowingControlsController(SowingControlCommandService sowingControlCommandService, SowingControlQueryService sowingControlQueryService) {
        this.sowingControlCommandService = sowingControlCommandService;
        this.sowingControlQueryService = sowingControlQueryService;
    }

   @PostMapping("/{sowingId}/controls")
public ResponseEntity<SowingControlResource> createSowingControl(@PathVariable Long sowingId, @RequestBody CreateSowingControlResource sowingControlResource) {

        var createSowingControlCommand = CreateSowingControlCommandFromResourceAssembler.fromResource(sowingId, sowingControlResource);
        var sowingControlId = sowingControlCommandService.handle(createSowingControlCommand);

    if(sowingControlId == 0L)
        return ResponseEntity.badRequest().build();

    var getSowingControlByIdQuery = new GetSowingControlByIdQuery(sowingControlId);
    var sowingControlOptional = sowingControlQueryService.handle(getSowingControlByIdQuery);

    if(sowingControlOptional.isEmpty())
        return ResponseEntity.badRequest().build();

    var sowingControlEntity = sowingControlOptional.get();
    var sowingControlResourceResponse = SowingControlResourceFromEntityAssembler.toResourceFromEntity(sowingControlEntity);

    return new ResponseEntity<>(sowingControlResourceResponse, HttpStatus.CREATED);
}
    @GetMapping("/{sowingId}/controls")
    public ResponseEntity<List<SowingControlResource>> getAllSowingControlsBySowingId(@PathVariable Long sowingId) {
        var getAllSowingControlsBySowingIdQuery = new GetSowingControlsBySowingIdQuery(sowingId);
        var sowingControls = sowingControlQueryService.handle(getAllSowingControlsBySowingIdQuery);
        var sowingControlResources = sowingControls.stream()
                .map(SowingControlResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sowingControlResources);
    }
    @DeleteMapping("/{sowingId}/controls/{sowingControlId}")
    public ResponseEntity<?> deleteSowingControl(@PathVariable Long sowingId, @PathVariable Long sowingControlId) {
    var deleteSowingControlCommand = new DeleteSowingControlBySowingIdCommand(sowingId, sowingControlId);
    sowingControlCommandService.handle(deleteSowingControlCommand);
    return ResponseEntity.ok("SowingControl with given id successfully deleted");
}
    /*@GetMapping
    public ResponseEntity<List<Object>> getAllSowingControls() {
        var getAllSowingControlsQuery = new GetAllSowingControlsQuery();
        var sowingControls = sowingControlQueryService.handle(getAllSowingControlsQuery);
        var sowingControlResources = sowingControls.stream()
                .map(SowingControlResource::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sowingControlResources);
    }*/
    //@GetMapping("/{sowingId}")
    //public ResponseEntity<SowingControlResource> getSowingControlBySowingId(@PathVariable Long sowingId) {}
}
