package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.valueobjects.ProductType;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.entities.AuditableModel;

@Getter
@Entity
public class Product extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Float quantity;

    @NotNull
    private ProductType productType;

    public Product(String name, Float quantity, ProductType productType) {
        this.name = name;
        this.quantity = quantity;
        this.productType = productType;
    }

    public Product() {
    }
}