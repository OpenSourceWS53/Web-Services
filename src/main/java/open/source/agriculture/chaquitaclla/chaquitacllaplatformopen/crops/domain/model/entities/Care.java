package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.entities.AuditableModel;

@Getter
@Entity
public class Care extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crop_id")
    @NotNull
    private Crop crop;

    @NotNull
    private String description;

    public Care(String description, Crop crop) {
        this.crop = crop;
        this.description = description;
    }
    public Care()
    {
    }

    public Care(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getCropId() {
        return crop.getId();
    }
}