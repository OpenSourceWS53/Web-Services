package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateDiseaseCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllDiseasesQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetDiseasesByCropIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.DiseaseCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.DiseaseQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.DiseaseResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.DiseaseResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/disease")
public class DiseaseController {

    private final DiseaseCommandService diseaseCommandService;
    private final DiseaseQueryService diseaseQueryService;

    public DiseaseController(DiseaseCommandService diseaseCommandService, DiseaseQueryService diseaseQueryService) {
        this.diseaseCommandService = diseaseCommandService;
        this.diseaseQueryService = diseaseQueryService;
    }

    @PostMapping
    public ResponseEntity<DiseaseResource> createDisease(@RequestBody CreateDiseaseCommand command) {
        Long diseaseId = diseaseCommandService.handle(command);
        DiseaseResource diseaseResource = new DiseaseResource(diseaseId, command.name(), command.description(), command.solution());
        return new ResponseEntity<>(diseaseResource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiseaseResource> getDisease(@PathVariable Long id) {
        return diseaseQueryService.handle(new GetDiseasesByCropIdQuery(id))
                .map(disease -> new DiseaseResource(disease.getId(), disease.getName(), disease.getDescription(), disease.getSolution()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DiseaseResource>> getAllDiseases() {
        var getAllDiseasesQuery = new GetAllDiseasesQuery();
        var diseases = diseaseQueryService.handle(getAllDiseasesQuery);
        var diseaseResources = diseases.stream().map(DiseaseResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(diseaseResources);
    }
}