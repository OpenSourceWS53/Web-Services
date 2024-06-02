package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates;

import jakarta.persistence.*;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.entities.AuditableModel;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.Country;


@Entity
public class City extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    //@ManyToOne
   // @JoinColumn(name = "country_id", nullable = false)
   // private Country country;
    

}
