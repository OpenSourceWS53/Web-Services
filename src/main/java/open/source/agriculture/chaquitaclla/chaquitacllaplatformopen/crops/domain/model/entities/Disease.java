package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates.Crop;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Disease {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @NotNull
    @Size(max = 30)
    private String Name;

    @Getter
    @NotNull
    @Size(max = 500)
    private String Description;

    @NotNull
    @Size(max = 500)
    private String Solution;

    @ManyToMany
    @JoinTable(
            name = "crop_diseases",
            joinColumns = @JoinColumn(name = "disease_id"),
            inverseJoinColumns = @JoinColumn(name = "crop_id")
    )
    private Set<Crop> crops = new HashSet<>();

    public Disease(String Name, String Description, String Solution){
        this.Name = Name;
        this.Description = Description;
        this.Solution = Solution;
    }
    public Disease(){
    }

}