package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.valueobjects.DateRange;

public record UpdateSowingResource(DateRange dateRange, int areaLand) {
}
