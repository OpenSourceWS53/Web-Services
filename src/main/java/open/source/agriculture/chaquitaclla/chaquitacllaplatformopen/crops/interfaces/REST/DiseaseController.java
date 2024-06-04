package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateDiseaseCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.*;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.DiseaseCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.DiseaseQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.DiseaseResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropQueryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/disease")
public class DiseaseController {

    private final DiseaseCommandService diseaseCommandService;
    private final DiseaseQueryService diseaseQueryService;
    private final CropQueryService cropQueryService;

    public DiseaseController(DiseaseCommandService diseaseCommandService, DiseaseQueryService diseaseQueryService, CropQueryService cropQueryService) {
        this.diseaseCommandService = diseaseCommandService;
        this.diseaseQueryService = diseaseQueryService;
        this.cropQueryService = cropQueryService;
    }

    @PostMapping
    public ResponseEntity<DiseaseResource> createDisease(@RequestBody CreateDiseaseCommand command) {
        Long diseaseId = diseaseCommandService.handle(command);
        DiseaseResource diseaseResource = new DiseaseResource(diseaseId, command.name(), command.description(), command.solution());
        return new ResponseEntity<>(diseaseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{cropId}/diseases")
    public ResponseEntity<List<DiseaseResource>> getDiseasesByCropId(@PathVariable Long cropId) {
        var diseases = diseaseQueryService.handle(new GetAllDiseasesQuery());
        var diseaseResources = diseases.stream()
                .map(disease -> new DiseaseResource(disease.getId(), disease.getName(), disease.getDescription(), disease.getSolution()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(diseaseResources);
    }
}