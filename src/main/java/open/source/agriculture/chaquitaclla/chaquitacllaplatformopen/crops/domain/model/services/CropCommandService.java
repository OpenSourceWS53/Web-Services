package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCropCommand;

import java.util.Optional;

public interface CropCommandService {
    Long handle(CreateCropCommand command);
}
