package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.valueobjects.ProductType;

public record ProductResource(Long id, String name, String description, ProductType productType) {
}
