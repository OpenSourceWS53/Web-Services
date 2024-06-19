package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.City;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.CityResource;

public class CityResourceFromEntityAssembler {
    public static CityResource toResourceFromEntity(City entity){
        return new CityResource(entity.getId(),entity.getName(),entity.getId());
    }
}
