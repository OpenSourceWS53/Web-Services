package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 500)
    private String Description;

    @NotNull
    @Size(max = 30)
    private String Name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "crop_diseases",
            joinColumns = @JoinColumn(name = "crop_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private Set<Disease> diseases;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "crop_pests",
            joinColumns = @JoinColumn(name = "crop_id"),
            inverseJoinColumns = @JoinColumn(name = "pest_id"))
    private Set<Pest> pests;


    public Crop(){
        diseases = new HashSet<>();
        pests = new HashSet<>();
    }

    public Crop(String name,String description){
        this();
        this.Name = name;
        this.Description = description;
    }
    public Crop(String name, String description, List<Disease> diseases, List<Pest> pests){
        this(name, description);
        addDiseases(diseases);
        addPests(pests);
    }

    public Crop addDisease(Disease disease) {
        this.diseases.add(disease);
        return this;
    }

    public Crop addPest(Pest pest) {
        this.pests.add(pest);
        return this;
    }

    public Crop addDiseases(List<Disease> diseases) {
        this.diseases.addAll(diseases);
        return this;
    }

    public Crop addPests(List<Pest> pests) {
        this.pests.addAll(pests);
        return this;
    }
}
