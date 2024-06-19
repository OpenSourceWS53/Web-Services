package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.Country;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.interfaces.rest.resources.CountryResource;

public class CountryResourceFromEntityAssembler {
    public static CountryResource toResourceFromEntity(Country entity){
        return new CountryResource(entity.getId(), entity.getName());
    }
}
