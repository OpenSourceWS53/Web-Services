package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources;

import java.util.List;

public record CreateCropResource(String name, String description, List<Long> diseaseIds, List<Long> pestIds) {
}