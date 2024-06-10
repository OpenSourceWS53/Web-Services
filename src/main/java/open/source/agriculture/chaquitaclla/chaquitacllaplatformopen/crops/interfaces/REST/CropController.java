package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCropCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllCropsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetCropByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.DiseaseQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.PestQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.DiseaseRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.PestRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CreateCropResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CropResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.DiseaseResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.UpdateCropResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.CreateCropCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.CropResourceFromEntityAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.UpdateCropCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.DeleteCropCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/v1/crops")  
public class CropController {

    private final CropCommandService cropCommandService;
    private final CropQueryService cropQueryService;

    @Autowired
    private CropRepository cropRepository;


    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private PestRepository pestRepository;

    public CropController(CropCommandService cropCommandService, CropQueryService cropQueryService) {
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
    }

    @Autowired
    private DiseaseQueryService diseaseQueryService;

    @Autowired
    private PestQueryService pestQueryService;

    @PostMapping
    public ResponseEntity<CropResource> createCrop(@RequestBody CreateCropResource cropResource) {
        Set<Disease> diseases = cropResource.diseases().stream()
                .map(diseaseId -> diseaseRepository.findById(Math.toIntExact(diseaseId))
                        .orElseThrow(() -> new NoSuchElementException("Disease not found")))
                .collect(Collectors.toSet());

        Set<Pest> pests = cropResource.pests().stream()
                .map(pestId -> pestRepository.findById(Math.toIntExact(pestId))
                        .orElseThrow(() -> new NoSuchElementException("Pest not found")))
                .collect(Collectors.toSet());

        Crop crop = new Crop(cropResource.name(), cropResource.description(), diseases, pests);
        cropRepository.save(crop);

        CropResource cropResourceResponse = CropResourceFromEntityAssembler.toResourceFromEntity(crop);
        return ResponseEntity.ok(cropResourceResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropResource> getCrop(@PathVariable Long id) {
        return cropQueryService.handle(new GetCropByIdQuery(id))
                .map(crop -> new CropResource(crop.getId(), crop.getName(), crop.getDescription(), crop.getDiseaseIds(), crop.getPestIds()))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CropResource>> getAllCrops() {
        var getAllCropsQuery = new GetAllCropsQuery();
        var crops = cropQueryService.handle(getAllCropsQuery);
        var cropResources = crops.stream().map(CropResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(cropResources);
    }

    @PutMapping("/{cropId}")
    public ResponseEntity<CropResource> updateCrop(@PathVariable Long cropId, @RequestBody UpdateCropResource updateCropResource) {
        var updateCropCommand = UpdateCropCommandFromResourceAssembler.toCommandFromResource(cropId, updateCropResource);
        var updatedCrop = cropCommandService.handle(updateCropCommand);
        if (updatedCrop.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var cropResource = CropResourceFromEntityAssembler.toResourceFromEntity(updatedCrop.get());
        return ResponseEntity.ok(cropResource);
    }

    @DeleteMapping("/{cropId}")
    public ResponseEntity<?> deleteCrop(@PathVariable Long cropId) {
        var deleteCropCommand = new DeleteCropCommand(cropId);
        cropCommandService.handle(deleteCropCommand);
        return ResponseEntity.ok("Crop with given id successfully deleted");
    }

}