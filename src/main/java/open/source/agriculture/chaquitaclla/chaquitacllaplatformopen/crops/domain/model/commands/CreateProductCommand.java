package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.valueobjects.ProductType;

public record CreateProductCommand(String name, Float quantity, ProductType productType) {
}