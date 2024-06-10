package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;

public record CreateCareResource(String description, Long cropId) {
}