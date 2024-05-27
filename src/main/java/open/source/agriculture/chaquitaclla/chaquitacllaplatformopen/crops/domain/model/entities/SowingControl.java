package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Sowing;

import java.util.Date;

@Getter
@Entity
@Embeddable
public class SowingControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sowing_id")
    @NotNull
    private Sowing sowing;

    private Date controlDate;

    @NotNull
    private String conditionDescription;
    @NotNull
    private String stemDescription;
    @NotNull
    private String soilMoistureDescription;


    public SowingControl(Sowing sowing, String conditionDescription, String stemDescription, String soilMoistureDescription) {
        this.sowing = sowing;
        this.conditionDescription = conditionDescription;
        this.stemDescription = stemDescription;
        this.soilMoistureDescription = soilMoistureDescription;
    }
    public SowingControl(){
    }

    public void controlCreated() {
        this.controlDate = new Date();
    }
}
