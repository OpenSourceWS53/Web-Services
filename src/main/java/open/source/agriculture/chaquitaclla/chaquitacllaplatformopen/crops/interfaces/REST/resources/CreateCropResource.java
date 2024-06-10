package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.interfaces.REST.resources;

import java.util.List;
import java.util.Set;

    public record CreateCropResource(String name, String description, List<Long> diseases, List<Long> pests) {
}