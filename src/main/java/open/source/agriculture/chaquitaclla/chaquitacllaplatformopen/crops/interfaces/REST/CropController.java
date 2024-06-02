package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.DiseaseRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.PestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crop")
public class CropController {
    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private PestRepository pestRepository;

    @PostMapping(value = "/createCrop")
    public String createCrop(@RequestBody Crop entity) {
        Crop crop = new Crop(entity.getName(), entity.getDescription());
        crop = cropRepository.save(crop);
        return "Crop saved!!!";
    }

    @PostMapping(value = "/addDiseaseToCrop/{cropId}")
    public String addDiseaseToCrop(@RequestBody Disease entity,
                                   @PathVariable(name = "cropId") Long cropId) {
        Crop crop = cropRepository.getById(cropId);
        Disease disease = new Disease(entity.getName(), entity.getDescription(), entity.getSolution());
        disease = diseaseRepository.save(disease);
        crop.addDisease(disease);
        crop = cropRepository.save(crop);
        return "Disease added to Crop!!!";
    }

    @PostMapping(value = "/addPestToCrop/{cropId}")
    public String addPestToCrop(@RequestBody Pest entity,
                                @PathVariable(name = "cropId") Long cropId) {
        Crop crop = cropRepository.getById(cropId);
        Pest pest = new Pest(entity.getName(), entity.getDescription(), entity.getSolution());
        pest = pestRepository.save(pest);
        crop.addPest(pest);
        crop = cropRepository.save(crop);
        return "Pest added to Crop!!!";
    }

    @GetMapping(value = "/getCrop/{cropId}")
    public String getCrop(@PathVariable(name = "cropId") Long cropId) {
        Crop crop = cropRepository.getById(cropId);
        return "Crop fetched successfully!!!";
    }
}
