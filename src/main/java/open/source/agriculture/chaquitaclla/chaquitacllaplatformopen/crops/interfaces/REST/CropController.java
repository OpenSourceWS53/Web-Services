package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCropCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetAllCropsQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetCropByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CropResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.CropResourceFromEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/v1/crops")
public class CropController {

    private final CropCommandService cropCommandService;
    private final CropQueryService cropQueryService;

    public CropController(CropCommandService cropCommandService, CropQueryService cropQueryService) {
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
    }

    @PostMapping
    public ResponseEntity<CropResource> createCrop(@RequestBody CreateCropCommand command) {
        Long cropId = cropCommandService.handle(command);
        CropResource cropResource = new CropResource(cropId, command.name(), command.description());
        return new ResponseEntity<>(cropResource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropResource> getCrop(@PathVariable Long id) {
        return cropQueryService.handle(new GetCropByIdQuery(id))
                .map(crop -> new CropResource(crop.getId(), crop.getName(), crop.getDescription()))
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

}