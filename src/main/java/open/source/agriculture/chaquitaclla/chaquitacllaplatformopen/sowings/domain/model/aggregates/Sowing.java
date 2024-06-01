package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.products.domain.model.entities.Product;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.entities.SowingControl;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.PhenologicalPhase;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.valueobjects.DateRange;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.sowings.domain.model.valueobjects.ProfileId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Setter
@Entity
public class Sowing extends AbstractAggregateRoot<Sowing> {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Embedded
    private DateRange dateRange;

    @Embedded
    private ProfileId profileId;

    @Getter
    @NotNull
    private int areaLand;

    @Getter
    @NotNull
    private boolean status;

    @OneToMany(mappedBy = "sowing", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SowingControl> sowingControls;


    @ManyToOne
    @JoinColumn(name = "crop_id")
    private Crop crop;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sowing_products",
            joinColumns = @JoinColumn(name = "sowing_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

    private PhenologicalPhase phenologicalPhase;

    public Sowing(DateRange dateRange, int areaLand, ProfileId profileId){
        this.dateRange = dateRange;
        this.areaLand = areaLand;
        this.profileId= profileId;
        this.phenologicalPhase = PhenologicalPhase.GERMINATION;
    }


    public void addSowingControl(SowingControl sowingControl) {
        if (sowingControls == null) {
            sowingControls = new ArrayList<>();
        }
        sowingControls.add(sowingControl);
        sowingControl.setSowing(this);
    }

    public void germinationPhase(){
        this.phenologicalPhase = PhenologicalPhase.GERMINATION;
    }
    public void harvestingPhase(){
        this.phenologicalPhase = PhenologicalPhase.HARVEST_READY;
    }
}