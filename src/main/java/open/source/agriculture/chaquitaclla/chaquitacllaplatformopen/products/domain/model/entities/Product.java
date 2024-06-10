package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.commands.CreateProductCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.valueobjects.ProductType;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates.Sowing;

import java.util.Set;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Float quantity;

    @NotNull
    private String description;

    @NotNull
    private ProductType productType;

    @ManyToMany
    @JoinTable(
            name = "product_sowing",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "sowing_id")
    )
    Set<Sowing> ProductSowings;


    public Product(CreateProductCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.quantity = command.quantity();
        this.productType = command.productType();
    }

}