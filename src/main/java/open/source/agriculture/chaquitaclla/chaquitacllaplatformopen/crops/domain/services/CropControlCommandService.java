package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCropControlCommand;

public interface CropControlCommandService {
    Long handle(CreateCropControlCommand command);
}
