package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.infrastructure.persistence.jpa.repositories;


import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductId(Long productId);
    List<Product> findProductsBySowingId(Long sowingId);
}
