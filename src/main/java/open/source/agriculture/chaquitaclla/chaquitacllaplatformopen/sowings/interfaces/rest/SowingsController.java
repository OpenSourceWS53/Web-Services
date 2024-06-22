package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllQuestionsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.QuestionResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform.QuestionResourceFromEntityAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.queries.GetAllSowingsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.infrastructure.persistence.jpa.repositories.SowingRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.CreateSowingResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.SowingResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform.CreateSowingCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform.SowingResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/sowings", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sowings", description = "Sowing Management Endpoints")
public class SowingsController {
    private final SowingCommandService sowingCommandService;
    private final SowingQueryService sowingQueryService;
    private final SowingRepository sowingRepository;

    public SowingsController(SowingCommandService sowingCommandService,
                             SowingRepository sowingRepository,
                             SowingQueryService sowingQueryService) {
        this.sowingCommandService = sowingCommandService;
        this.sowingRepository = sowingRepository;
        this.sowingQueryService = sowingQueryService;
    }

    @PostMapping
    public ResponseEntity<SowingResource> createSowing(@RequestBody CreateSowingResource createSowingResource) {
        var createSowingCommand = CreateSowingCommandFromResourceAssembler.fromResource(createSowingResource);
        var sowingId = sowingCommandService.handle(createSowingCommand);
        if(sowingId == 0L) return ResponseEntity.badRequest().build();

        // Retrieve the newly created Sowing from the database
        var sowing = sowingRepository.findById(sowingId).orElseThrow();

        // Convert the Sowing entity to a SowingResource
        var sowingResourceCreated = SowingResourceFromEntityAssembler.fromEntity(sowing);

        return new ResponseEntity<>(sowingResourceCreated, HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<SowingResource>> getAllSowings(){
        var getAllSowingsQuery = new GetAllSowingsQuery();
        var sowings = sowingQueryService.handle(getAllSowingsQuery);
        var sowingResource = sowings.stream()
                .map(SowingResourceFromEntityAssembler::fromEntity)
                .toList();
        return ResponseEntity.ok(sowingResource);
    }
}