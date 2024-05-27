package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates;

import jakarta.persistence.*;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Disease;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities.Pest;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.HashSet;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Crop extends AbstractAggregateRoot<Crop> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
