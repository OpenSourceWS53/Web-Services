package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.entities.SowingControl;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingCondition;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingSoilMoisture;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.SowingStemCondition;

public record SowingControlResource(Long SowingId,
                                    @NotNull SowingCondition sowingCondition,
                                    @NotNull SowingSoilMoisture sowingSoilMoisture,
                                    @NotNull SowingStemCondition sowingStemCondition) {
    public static Object fromEntity(SowingControl sowingControl) {
        return null;
    }
}