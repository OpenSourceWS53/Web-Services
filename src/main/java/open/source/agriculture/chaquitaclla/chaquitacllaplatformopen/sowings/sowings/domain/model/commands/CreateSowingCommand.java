package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.sowings.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.valueobjects.DateRange;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.CropId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.ProfileId;

public record CreateSowingCommand(DateRange dateRange, CropId cropId, Integer areaLand, ProfileId profileId){
}
