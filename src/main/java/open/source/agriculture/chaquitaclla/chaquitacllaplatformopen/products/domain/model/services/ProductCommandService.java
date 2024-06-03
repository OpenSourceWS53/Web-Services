package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands.CreateCropCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.commands.CreateProductCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.commands.DeleteProductCommand;

public interface ProductCommandService {
    Long handle(CreateProductCommand command);
    void handle(DeleteProductCommand command);
}
