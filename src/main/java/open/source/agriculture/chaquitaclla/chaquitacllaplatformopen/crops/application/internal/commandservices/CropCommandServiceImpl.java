package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCropCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.services.CropCommandService;

public class CropCommandServiceImpl implements CropCommandService {
    private final CropCommandService cropCommandService;

    public CropCommandServiceImpl(CropCommandService cropCommandService) {
        this.cropCommandService = cropCommandService;
     ProjectBranch
        return null;
    }
}
        Crop crop = new Crop(command.name(), command.description(), command.diseases(), command.pests());
        crop = cropRepository.save(crop);
        return crop.getId();
    }

    @Override
    public Optional<Crop> handle(UpdateCropCommand command) {
        return cropRepository.findById(command.cropId()).map(existingCrop -> {
            existingCrop.setName(command.name());
            existingCrop.setDescription(command.description());
            return cropRepository.save(existingCrop);
        });
    }

    @Override
    public void handle(DeleteCropCommand command) {
        cropRepository.deleteById(command.cropId());
    }

    @Override
    public void save(Crop crop) {
        cropRepository.save(crop);
    }
}
