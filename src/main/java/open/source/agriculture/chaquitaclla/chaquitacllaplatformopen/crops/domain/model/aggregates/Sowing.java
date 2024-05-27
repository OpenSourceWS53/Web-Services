package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.valueobjects.PhenologicalPhase;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.valueobjects.DateRange;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Sowing {
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private PhenologicalPhase phenologicalPhase;

    public Sowing(){
    }
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