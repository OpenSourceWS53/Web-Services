package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.entities.AuditableModel;

@Getter
@Setter
@Entity
public class Country extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //contructor

    public Country(String name) {
        this.name = name;
    }

    public Country() {

    }
}
