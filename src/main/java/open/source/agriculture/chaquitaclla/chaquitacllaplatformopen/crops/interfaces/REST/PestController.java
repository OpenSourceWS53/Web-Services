package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreatePestCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.*;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.PestCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.PestQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.PestResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.PestResourceFromEntityAssembler;
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

    public PestController(PestCommandService pestCommandService, PestQueryService pestQueryService, CropQueryService cropQueryService, CropCommandService cropCommandService) {
        this.pestCommandService = pestCommandService;
        this.pestQueryService = pestQueryService;
        this.cropQueryService = cropQueryService;
        this.cropCommandService = cropCommandService;
    }

    @PostMapping
    public ResponseEntity<PestResource> createPest(@RequestBody CreatePestCommand command) {
        Crop crop = cropQueryService.findById(command.cropId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Crop not found"));

        Long pestId = pestCommandService.handle(command);
        PestResource pestResource = new PestResource(pestId, command.name(), command.description(), command.solution(), command.cropId());

        crop.addPest(pestQueryService.findPestById(pestId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pest not found")));

        cropCommandService.save(crop);

        return new ResponseEntity<>(pestResource, HttpStatus.CREATED);
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
        var pests = pestQueryService.handle(new GetAllPestsQuery());
        var pestResources = pests.stream()
                .map(pest -> new PestResource(pest.getId(), pest.getName(), pest.getDescription(), pest.getSolution(), pest.getCropId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pestResources);
    }
}