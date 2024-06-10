package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Care;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCareCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllCaresQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllDiseasesQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetCaresByCropIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CareCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CareQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.CareRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CreateCareResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CareResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.DiseaseResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.CreateCareSourceCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.CareResourceFromEntityAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cares")
public class CareController {

    private final CareCommandService careCommandService;
    private final CareQueryService careQueryService;
    private final CropCommandService cropCommandService;
    private final CropQueryService cropQueryService;

    public CareController(CareCommandService careCommandService,
                          CareQueryService careQueryService,
                          CropCommandService cropCommandService,
                            CropQueryService cropQueryService) {
        this.careCommandService = careCommandService;
        this.careQueryService = careQueryService;
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
    }

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private CareRepository careRepository;

    @PostMapping
    public ResponseEntity<CareResource> createCare(@RequestBody CreateCareResource careResource) {
        Crop crop = cropRepository.findById(careResource.cropId())
                .orElseThrow(() -> new NoSuchElementException("Crop not found"));

        Care care = new Care(careResource.description(), crop);
        careRepository.save(care);

        CareResource careResourceResponse = CareResourceFromEntityAssembler.toResourceFromEntity(care);
        return ResponseEntity.ok(careResourceResponse);
    }

    @GetMapping
    public ResponseEntity<List<CareResource>> getAllCares() {
        var getAllCaresQuery = new GetAllCaresQuery();
        var cares = careQueryService.handle(getAllCaresQuery);
        var careResource = cares.stream().map(CareResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(careResource);
    }

    @GetMapping("/{cropId}")
    public ResponseEntity<List<CareResource>> getCaresByCropId(@PathVariable Long cropId) {
        List<Care> cares = careRepository.findByCrop_Id(cropId);
        if (cares.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<CareResource> careResources = cares.stream()
                .map(CareResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(careResources);
    }
}