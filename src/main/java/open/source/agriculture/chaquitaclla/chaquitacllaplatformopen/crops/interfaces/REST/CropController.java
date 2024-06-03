package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CreateCropResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.CropResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.CreateCropSourceCommandFromResourceAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform.CropResourceFromEntityAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCropCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/crops")
public class CropController {

    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private CreateCropSourceCommandFromResourceAssembler createCropSourceCommandFromResourceAssembler;

    @Autowired
    private CropResourceFromEntityAssembler cropResourceFromEntityAssembler;

    @PostMapping
    public ResponseEntity<CropResource> createCrop(@RequestBody CreateCropResource resource) throws URISyntaxException {
        CreateCropCommand createCropCommand = createCropSourceCommandFromResourceAssembler.toCommand(resource);
        Crop crop = new Crop(createCropCommand.name(), createCropCommand.description(), createCropCommand.diseases(), createCropCommand.pests());
        crop = cropRepository.save(crop);
        CropResource cropResource = cropResourceFromEntityAssembler.toResource(crop);
        return ResponseEntity.created(new URI("/api/v1/crops/" + cropResource.id())).body(cropResource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CropResource> getCropById(@PathVariable Long id) {
        Crop crop = cropRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Crop not found with id " + id));
        CropResource cropResource = cropResourceFromEntityAssembler.toResource(crop);
        return ResponseEntity.ok(cropResource);
    }

    @GetMapping
    public ResponseEntity<List<CropResource>> getAllCrops() {
        List<Crop> crops = cropRepository.findAll();
        List<CropResource> cropResources = crops.stream()
                .map(cropResourceFromEntityAssembler::toResource)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cropResources);
    }
}