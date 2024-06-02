package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/disease")
public class DiseaseController {
    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private CropRepository cropRepository;

    @PostMapping(value = "/createDisease")
    public String createDisease(@RequestBody Disease entity) {
        Disease disease = new Disease(entity.getName(), entity.getDescription(), entity.getSolution());
        disease = diseaseRepository.save(disease);
        return "Disease saved!!!";
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

    @GetMapping(value = "/getDisease/{diseaseId}")
    public String getDisease(@PathVariable(name = "diseaseId") Integer diseaseId) {
        Disease disease = diseaseRepository.getById(diseaseId);
        return "Disease fetched successfully!!!";
    }
}