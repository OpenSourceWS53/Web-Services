package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.interfaces.rest.resources;



public record CreateSowingControlResource(Long sowingId,
                                          Long sowingCondition,
                                          Long sowingSoilMoisture,
                                          Long sowingStemCondition) {
}
