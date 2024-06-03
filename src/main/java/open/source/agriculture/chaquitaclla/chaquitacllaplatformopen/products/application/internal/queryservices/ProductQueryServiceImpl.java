package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.application.internal.queryservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.entities.Product;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.queries.GetProductsBySowingIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.services.ProductQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Optional<Product> handle(GetProductsBySowingIdQuery query) {
        return productRepository.findById(query.productId());
    }

}
