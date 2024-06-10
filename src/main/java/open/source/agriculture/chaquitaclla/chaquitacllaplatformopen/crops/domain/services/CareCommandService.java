package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCareCommand;

public interface CareCommandService {
    void createCare(CreateCareCommand command);

    Long handle(CreateCareCommand command);

}