package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.PestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pest")
public class PestController {
    @Autowired
    private PestRepository pestRepository;

    @Autowired
    private CropRepository cropRepository;

    @PostMapping(value = "/createPest")
    public String createPest(@RequestBody Pest entity) {
        Pest pest = new Pest(entity.getName(), entity.getDescription(), entity.getSolution());
        pest = pestRepository.save(pest);
        return "Pest saved!!!";
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

    @GetMapping(value = "/getPest/{pestId}")
    public String getPest(@PathVariable(name = "pestId") Integer pestId) {
        Pest pest = pestRepository.getById(pestId);
        return "Pest fetched successfully!!!";
    }
}