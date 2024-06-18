package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.sowings.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates.Sowing;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.CreateSowingCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.DeleteSowingCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.commands.UpdateSowingCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.services.SowingCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.infrastructure.persistence.jpa.repositories.SowingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SowingCommandServiceImpl implements SowingCommandService {
    private final SowingRepository sowingRepository;

    public SowingCommandServiceImpl(SowingRepository sowingRepository) {
        this.sowingRepository = sowingRepository;
    }
    @Override
    public Long handle(CreateSowingCommand command) {
        var sowing = new Sowing(command.dateRange(), command.cropId().cropId().intValue(), command.profileId());
        sowingRepository.save(sowing);
        return sowing.getId();
    }
    @Override
    public Optional<Sowing> handle(UpdateSowingCommand command) {
        if (!sowingRepository.existsById(command.Id()))
            throw new IllegalArgumentException("Sowing does not exist");

        var sowingToUpdate = sowingRepository.findById(command.Id()).get();


        sowingToUpdate.setDateRange(command.dateRange());
        sowingToUpdate.setAreaLand(command.areaLand());

        var updatedSowing = sowingRepository.save(sowingToUpdate);

        return Optional.of(updatedSowing);
    }
    @Override
    public void handle(DeleteSowingCommand command) {
        if (!sowingRepository.existsById(command.sowingId()))
            throw new IllegalArgumentException("Sowing does not exist");

        sowingRepository.deleteById(command.sowingId());
    }
}
