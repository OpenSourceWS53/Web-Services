package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCropCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.DeleteCropCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.UpdateCropCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CropCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CropCommandServiceImpl implements CropCommandService {

    private final CropRepository cropRepository;

    @Autowired
    public CropCommandServiceImpl(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    @Override
    public Long handle(CreateCropCommand command) {
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
}