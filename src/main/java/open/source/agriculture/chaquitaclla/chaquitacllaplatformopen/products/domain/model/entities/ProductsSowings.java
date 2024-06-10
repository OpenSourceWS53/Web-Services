package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.aggregates.Product;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates.Sowing;

@Getter
@Setter
@Entity
public class ProductsSowings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sowing_id")
    private Sowing sowing;

    @NotNull
    private Float quantity;
}
