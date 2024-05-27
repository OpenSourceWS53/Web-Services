package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Sowing;

@Getter
@Entity
@Embeddable
public class Care {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_id")
    @NotNull
    private Crop crop;

    @NotNull
    private String description;

    public Care(Crop crop, String description) {
        this.crop = crop;
        this.description = description;
    }
    public Care(){

    }
}
