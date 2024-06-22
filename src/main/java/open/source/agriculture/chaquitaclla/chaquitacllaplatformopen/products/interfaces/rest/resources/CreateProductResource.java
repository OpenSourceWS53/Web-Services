package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.valueobjects.ProductType;

public record CreateProductResource(String name, String description, ProductType productType) {
}
