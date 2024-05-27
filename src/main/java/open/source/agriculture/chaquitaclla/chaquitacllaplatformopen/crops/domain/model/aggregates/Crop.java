package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.crops.domain.model.aggregates;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Crop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Crop(){
    }
}
