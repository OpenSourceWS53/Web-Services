package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateDiseaseCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.*;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.DiseaseCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.DiseaseQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.DiseaseResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.DiseaseResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/diseases")
public class DiseaseController {

    private final DiseaseCommandService diseaseCommandService;
    private final DiseaseQueryService diseaseQueryService;
    private final CropQueryService cropQueryService;
    private final CropCommandService cropCommandService;

    public DiseaseController(DiseaseCommandService diseaseCommandService, DiseaseQueryService diseaseQueryService, CropQueryService cropQueryService, CropCommandService cropCommandService) {
        this.diseaseCommandService = diseaseCommandService;
        this.diseaseQueryService = diseaseQueryService;
        this.cropQueryService = cropQueryService;
        this.cropCommandService = cropCommandService;
    }

    @PostMapping
    public ResponseEntity<DiseaseResource> createDisease(@RequestBody CreateDiseaseCommand command) {
        Crop crop = cropQueryService.findById(command.cropId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Crop not found"));

        Long diseaseId = diseaseCommandService.handle(command);
        DiseaseResource diseaseResource = new DiseaseResource(diseaseId, command.name(), command.description(), command.solution(), command.cropId());

        crop.addDisease(diseaseQueryService.findById(diseaseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Disease not found")));

        cropCommandService.save(crop);

        return new ResponseEntity<>(diseaseResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DiseaseResource>> getAllDiseases() {
        var getAllDiseasesQuery = new GetAllDiseasesQuery();
        var diseases = diseaseQueryService.handle(getAllDiseasesQuery);
        var diseaseResource = diseases.stream().map(DiseaseResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(diseaseResource);
    }

    @GetMapping("/{cropId}")
    public ResponseEntity<List<DiseaseResource>> getDiseasesByCropId(@PathVariable Long cropId) {
        var diseases = diseaseQueryService.handle(new GetAllDiseasesQuery());
        var diseaseResources = diseases.stream()
                .map(disease -> new DiseaseResource(disease.getId(), disease.getName(), disease.getDescription(), disease.getSolution(), disease.getCropId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(diseaseResources);
    }
}