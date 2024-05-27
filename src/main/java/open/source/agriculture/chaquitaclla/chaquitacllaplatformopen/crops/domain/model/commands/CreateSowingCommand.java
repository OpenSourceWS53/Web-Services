package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.valueobjects.DateRange;

public record CreateSowingCommand(DateRange dateRange, Long cropId, Integer areaLand){
}
