package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.valueobjects.DateRange;

public record UpdateSowingCommand(Long sowingId, DateRange dateRange, int areaLand) {
}