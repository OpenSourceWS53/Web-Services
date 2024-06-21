package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.*;


import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.City;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.EmailUserRecord;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.NameUserRecord;
import org.springframework.data.domain.AbstractAggregateRoot;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.Password;


@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class User extends AbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private NameUserRecord name;

    @Embedded
    @AttributeOverride(name = "email", column = @Column(name = "email"))
    private EmailUserRecord email;

    @Embedded
    @AttributeOverride(name = "password", column = @Column(name = "password"))
    private Password password;

    @ManyToOne
    private City city;

    @ManyToOne
    private Subscription subscription;


    public User() {

    }

    public User(String firstName, String lastName, String email, String password, City city, Subscription subscription) {
        this.name = new NameUserRecord(firstName, lastName);
        this.email = new EmailUserRecord(email);
        this.password = new Password(password);
        this.city = city;
        this.subscription = subscription;
    }


}
