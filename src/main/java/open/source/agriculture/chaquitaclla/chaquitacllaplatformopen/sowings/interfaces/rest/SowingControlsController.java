package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetAllSowingControlsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingControlCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingControlQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.SowingControlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    //@PostMapping
    @PostMapping
    public ResponseEntity<SowingControlResource> createSowingControlCommand(@RequestBody SowingControlResource sowingControlResource) {
        //var createSowingControlCommand = CreateSowingControlCommandFromResourceAssembler.toCommandFromResource(sowingControlResource);
        //var sowingControlId = sowingControlCommandService.handle(createSowingControlCommand);
        //if(sowingControlId == 0L) return ResponseEntity.badRequest().build();
        //var getSowingControlByIdQuery = new GetSowingControlByIdQuery(sowingControlId);
        //var sowingControl = sowingControlQueryService.handle(getSowingControlByIdQuery);
        //if(sowingControl.isEmpty()) return ResponseEntity.badRequest().build();
        //var sowingControlResource = SowingControlResourceFromEntityAssembler.toResourceFromEntity(sowingControl.get());
        //return new ResponseEntity<>(sowingControlResource, HttpStatus.CREATED);
        return null;
    }


    @GetMapping
    public ResponseEntity<List<Object>> getAllSowingControls() {
        var getAllSowingControlsQuery = new GetAllSowingControlsQuery();
        var sowingControls = sowingControlQueryService.handle(getAllSowingControlsQuery);
        var sowingControlResources = sowingControls.stream()
                .map(SowingControlResource::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(sowingControlResources);
    }
    //@GetMapping("/{sowingId}")
    //public ResponseEntity<SowingControlResource> getSowingControlBySowingId(@PathVariable Long sowingId) {}
}
