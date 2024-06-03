package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCropCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.services.CropCommandService;

public class CropCommandServiceImpl implements CropCommandService {
    private final CropCommandService cropCommandService;

    public CropCommandServiceImpl(CropCommandService cropCommandService) {
        this.cropCommandService = cropCommandService;
    }

    @Override
    public Long handle(CreateCropCommand command) {
        return null;
    }
}
