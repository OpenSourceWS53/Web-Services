package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates.Sowing;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.CropId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.ProfileId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.SowingResource;

public class SowingResourceFromEntityAssembler {
    public static SowingResource fromEntity(Sowing sowing) {
        return new SowingResource(
                sowing.getId(),
                sowing.getDateRange(), // Asegúrate de que DateRange tiene un método toString() adecuado
                sowing.getProfileId().profileId().intValue(), // Convierte ProfileId a Integer
                sowing.getAreaLand(),
                sowing.isStatus(),
                sowing.getCropId().cropId().intValue(),
                sowing.getPhenologicalPhase().ordinal()
        );
    }
}