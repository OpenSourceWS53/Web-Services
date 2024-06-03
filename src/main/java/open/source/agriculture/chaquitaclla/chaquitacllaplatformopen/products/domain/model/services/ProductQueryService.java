package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetCropByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.entities.Product;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.queries.GetProductsBySowingIdQuery;

import java.util.Optional;

public interface ProductQueryService {
    Optional<Product> handle(GetProductsBySowingIdQuery query);
}
