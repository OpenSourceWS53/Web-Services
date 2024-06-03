package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources;

import java.util.List;

public record CropResource(Long id, String name, String description, List<DiseaseResource> diseases, List<PestResource> pests) {
}
