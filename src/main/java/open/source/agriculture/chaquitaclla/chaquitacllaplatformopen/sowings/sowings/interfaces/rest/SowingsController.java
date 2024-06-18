package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.sowings.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.CreateSowingResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.SowingResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform.CreateSowingCommandFromResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/sowings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sowings", description = "Sowing Management Endpoints")
public class SowingsController {
    private final SowingCommandService sowingCommandService;

    public SowingsController(SowingCommandService sowingCommandService) {
        this.sowingCommandService = sowingCommandService;
    }

    @PostMapping
    public ResponseEntity<SowingResource> createSowing(@RequestBody SowingResource sowingResource) {
        CreateSowingResource createSowingResource = new CreateSowingResource(
                sowingResource.dateRange(),
                sowingResource.cropId(),
                sowingResource.areaLand(),
                sowingResource.profileId()
        );
        var createSowingCommand = CreateSowingCommandFromResourceAssembler.fromResource(createSowingResource);
        var sowingId = sowingCommandService.handle(createSowingCommand);
        if(sowingId == 0L) return ResponseEntity.badRequest().build();
        return new ResponseEntity<>(sowingResource, HttpStatus.CREATED);
    }
}