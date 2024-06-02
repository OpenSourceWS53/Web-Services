package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Care;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;

import java.util.Date;

@Getter
@Setter
@Entity
@Embeddable
public class CropControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    @NotNull
    private Crop crop;

    @ManyToOne
    @JoinColumn(name = "care_id")
    private Care care;

    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    @ManyToOne
    @JoinColumn(name = "pest_id")
    private Pest pest;

    private Date controlDate;

    public CropControl(Crop crop, Care care, Disease disease, Pest pest) {
        this.crop = crop;
        this.care = care;
        this.disease = disease;
        this.pest = pest;
    }

    public CropControl(){
    }

    public void controlCreated() {
        this.controlDate = new Date();
    }
}