package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.queries.GetCropByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.entities.Product;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.queries.GetProductByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.queries.GetProductsBySowingIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    List<Product> handle(GetProductsBySowingIdQuery query);
    Optional<Product> handle(GetProductByIdQuery query);
}
