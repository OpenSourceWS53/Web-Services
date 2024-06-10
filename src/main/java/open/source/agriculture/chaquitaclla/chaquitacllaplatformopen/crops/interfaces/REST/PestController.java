package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreatePestCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.*;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.PestCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.PestQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.PestRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CreatePestResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.PestResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.CreatePestSourceCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.PestResourceFromEntityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/pests")
public class PestController {

    private final PestCommandService pestCommandService;
    private final PestQueryService pestQueryService;
    private final CropQueryService cropQueryService;
    private final CropCommandService cropCommandService;

    @Autowired
    private PestRepository pestRepository;

    public PestController(PestCommandService pestCommandService, PestQueryService pestQueryService, CropQueryService cropQueryService, CropCommandService cropCommandService) {
        this.pestCommandService = pestCommandService;
        this.pestQueryService = pestQueryService;
        this.cropQueryService = cropQueryService;
        this.cropCommandService = cropCommandService;
    }

    @PostMapping
    public ResponseEntity<PestResource> createPest(@RequestBody CreatePestResource resource) {
        var createPestCommand = CreatePestSourceCommandFromResourceAssembler.toCommandFromResource(resource);
        var pestId = pestCommandService.handle(createPestCommand);
        if(pestId == 0L) return ResponseEntity.badRequest().build();
        var getPestByIdQuery = new GetPestByIdQuery(pestId);
        var pest = pestQueryService.handle(getPestByIdQuery);
        if(pest.isEmpty()) return ResponseEntity.badRequest().build();
        var pestResource = PestResourceFromEntityAssembler.toResourceFromEntity(pest.get());
        return ResponseEntity.ok(pestResource);
    }

    @GetMapping
    public ResponseEntity<List<PestResource>> getAllPests() {
        var getAllPestsQuery = new GetAllPestsQuery();
        var pests = pestQueryService.handle(getAllPestsQuery);
        var pestResource = pests.stream().map(PestResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(pestResource);
    }

    @GetMapping("/{cropId}")
    public ResponseEntity<List<PestResource>> getPestsByCropId(@PathVariable Long cropId) {
        List<Pest> pests = pestRepository.findByCropId(cropId);
        if (pests.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<PestResource> pestResources = pests.stream()
                .map(PestResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(pestResources);
    }
}