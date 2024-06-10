package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.aggregates.Product;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.entities.ProductsSowings;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.PhenologicalPhase;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.valueobjects.DateRange;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Sowing extends AbstractAggregateRoot<Sowing> {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Embedded
    private DateRange dateRange;

    @Getter
    @NotNull
    private int areaLand;

    @Getter
    @NotNull
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @OneToMany(mappedBy = "sowing")
    private Set<ProductsSowings> associations;

    private PhenologicalPhase phenologicalPhase;

    public Sowing(DateRange dateRange, int areaLand, User user){
        this.dateRange = dateRange;
        this.areaLand = areaLand;
        this.user = user;
        this.phenologicalPhase = PhenologicalPhase.GERMINATION;
    }

    public void germinationPhase(){
        this.phenologicalPhase = PhenologicalPhase.GERMINATION;
    }
    public void harvestingPhase(){
        this.phenologicalPhase = PhenologicalPhase.HARVEST_READY;
    }
}