package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Crop extends AbstractAggregateRoot<Crop> {
    @Getter
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @NotNull
    @Size(max = 500)
    private String Description;

    @Getter
    @NotNull
    @Size(max = 30)
    private String Name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "crop_diseases",
            joinColumns = @JoinColumn(name = "crop_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private Set<Disease> diseases = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "crop_pests",
            joinColumns = @JoinColumn(name = "crop_id", foreignKey = @ForeignKey(name = "fk_crop_pests_crop")),
            inverseJoinColumns = @JoinColumn(name = "pest_id", foreignKey = @ForeignKey(name = "fk_crop_pests_pest"))
    )
    private Set<Pest> pests;

    public Crop(){
        diseases = new HashSet<>();
        pests = new HashSet<>();
    }

    public Crop(String name, String description, Set<Disease> diseases, Set<Pest> pests){
        this();
        this.Name = name;
        this.Description = description;
        this.diseases = diseases;
        this.pests = pests;
    }

    public Crop(String name, String description, List<Long> diseases, List<Long> pests) {
        this();
        this.Name = name;
        this.Description = description;
        this.diseases = new HashSet<>();
        this.pests = new HashSet<>();
    }

    public Crop addDisease(Disease disease) {
        this.diseases.add(disease);
        return this;
    }

    public Crop addPest(Pest pest) {
        this.pests.add(pest);
        return this;
    }

    public Crop addDiseases(Set<Disease> diseases) {
        this.diseases.addAll(diseases);
        return this;
    }

    public Crop addPests(Set<Pest> pests) {
        this.pests.addAll(pests);
        return this;
    }

    public Crop setName(String name) {
        this.Name = name;
        return this;
    }

    public Crop setDescription(String description) {
        this.Description = description;
        return this;
    }


}
