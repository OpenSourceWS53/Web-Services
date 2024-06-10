package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands;

import java.util.List;

public record UpdateCropCommand(Long cropId, String name, String description) {
    public Long cropId() {
        return cropId;
    }
}