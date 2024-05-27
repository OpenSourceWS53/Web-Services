package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 30)
    private String Name;

    @NotNull
    @Size(max = 500)
    private String Description;

    public Disease(String Name, String Description){
        this.Name = Name;
        this.Description = Description;
    }
    public Disease(){
    }
}
