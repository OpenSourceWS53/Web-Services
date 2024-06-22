package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.valueobjects.ProductType;

public record CreateProductCommand(String name, String description, ProductType productType) {
}