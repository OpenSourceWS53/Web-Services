package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;

import java.util.Set;

public record UpdateCropCommand(Long cropId, String name, String description) {
    public Long cropId() {
        return cropId;
    }

    public Set<Disease> diseases() {
        return diseases();
    }

    public Set<Pest> pests() {
        return pests();
    }
}