package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates.Sowing;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.CropId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.ProfileId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources.SowingResource;

public class SowingResourceFromEntityAssembler {
    public static SowingResource fromEntity(Sowing sowing) {
        CropId cropId = new CropId(sowing.getCropId().cropId());
        ProfileId profileId = new ProfileId(sowing.getProfileId().profileId());
        return new SowingResource(
                sowing.getId(),
                sowing.getDateRange(),
                cropId,
                sowing.getAreaLand(),
                profileId
        );
    }
}