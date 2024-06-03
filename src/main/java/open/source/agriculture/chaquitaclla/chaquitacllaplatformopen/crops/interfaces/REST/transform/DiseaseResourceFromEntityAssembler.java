package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources.DiseaseResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;

public class DiseaseResourceFromEntityAssembler {

    public DiseaseResource toResource(Disease entity) {
        return new DiseaseResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getSolution()
        );
    }
}