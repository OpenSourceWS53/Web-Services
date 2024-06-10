package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.CreateSowingControlCommand;

public interface SowingControlCommandService {
    Long handle(CreateSowingControlCommand command);

}
