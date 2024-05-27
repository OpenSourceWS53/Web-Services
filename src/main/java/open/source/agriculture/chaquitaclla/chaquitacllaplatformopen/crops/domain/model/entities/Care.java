package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Sowing;

@Getter
@Entity
@Embeddable
public class Care {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sowing_id")
    @NotNull
    private Sowing sowing;

    @NotNull
    private String description;

    public Care(Sowing sowing, String description) {
        this.sowing = sowing;
        this.description = description;
    }
    public Care(){

    }
}
