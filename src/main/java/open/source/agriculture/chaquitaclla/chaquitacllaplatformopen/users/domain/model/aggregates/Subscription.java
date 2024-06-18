package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.*;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.entities.AuditableModel;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.NameSubsCriptionRecord;


@Entity
@Getter
@Setter
public class Subscription extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "subscriptionName", column = @Column(name = "name"))
    private NameSubsCriptionRecord name;

    private String description;

    private Double price;

    //contructor

    public Subscription(String name, Double price,String description) {
        this.name = new NameSubsCriptionRecord(name);
        this.description = description;
        this.price = price;
    }


    public Subscription() {

    }
}
