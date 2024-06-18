package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCareCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Care;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.services.CareCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.infrastructure.persistence.jpa.repositories.CareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareCommandServiceImpl implements CareCommandService {

    private final CareRepository careRepository;

    @Autowired
    public CareCommandServiceImpl(CareRepository careRepository) {
        this.careRepository = careRepository;
    }

    @Override
    public void createCare(CreateCareCommand command) {
        createAndSaveCare(command);
    }

    @Override
    public Long handle(CreateCareCommand command) {
        return createAndSaveCare(command).getId();
    }

    @Override
    public void save(Care care) {
        careRepository.save(care);
    }

    private Care createAndSaveCare(CreateCareCommand command) {
        Care care = new Care(command.cropId(), command.description());
        careRepository.save(care);
        return care;
    }
}