package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.sowings.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.CreateSowingControlCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.DeleteSowingControlBySowingIdCommand;

public interface SowingControlCommandService {
    Long handle(CreateSowingControlCommand command);
    void handle(DeleteSowingControlBySowingIdCommand command);

}
