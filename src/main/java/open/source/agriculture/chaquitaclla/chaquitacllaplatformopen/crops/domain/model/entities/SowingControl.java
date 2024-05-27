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

    private Date control_date;

    @NotNull
    private String condition_description;
    @NotNull
    private String stem_description;
    @NotNull
    private String soil_moisture_description;


    public SowingControl(Sowing sowing, String condition_description, String stem_description, String soil_moisture_description) {
        this.sowing = sowing;
        this.condition_description = condition_description;
        this.stem_description = stem_description;
        this.soil_moisture_description = soil_moisture_description;
    }
    public SowingControl(){
    }

    public void controlCreated() {
        this.control_date = new Date();
    }
}
