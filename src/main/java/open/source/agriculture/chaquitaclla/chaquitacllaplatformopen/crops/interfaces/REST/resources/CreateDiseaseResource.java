package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;

public record CreateDiseaseResource(String name, String description, String solution, Long cropId) {
}