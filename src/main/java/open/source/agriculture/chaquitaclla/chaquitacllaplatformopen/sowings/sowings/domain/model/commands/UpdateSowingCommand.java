package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.sowings.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.valueobjects.DateRange;

public record UpdateSowingCommand(Long Id, DateRange dateRange, int areaLand) {
}