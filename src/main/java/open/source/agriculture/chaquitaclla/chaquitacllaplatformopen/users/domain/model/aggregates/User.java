package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.NameUserRecord;
import org.springframework.data.domain.AbstractAggregateRoot;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.EmailDirection;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.Password;

import java.util.Date;

@Entity
public class User extends AbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "subscriptionName", column = @Column(name = "firstName"))
    private NameUserRecord firstName;

    @Embedded
    @AttributeOverride(name = "subscriptionName", column = @Column(name = "lastName"))
    private NameUserRecord lastName;

    @Embedded
    @Email
    @AttributeOverride(name = "emialDirection",column = @Column(name = "email"))
    private EmailDirection email;

    @Embedded
    @AttributeOverride(name = "password",column = @Column(name = "password"))
    private Password password;

    @Getter
    @ManyToOne
    private City city;

    @Getter
    @ManyToOne
    private Subscription subscription;

    private Date star_date;
    private Date end_date;


    public User() {
    }

    public User(NameUserRecord firstName,NameUserRecord  lastName, EmailDirection email, Password password) {
        firstName.validate();
        lastName.validate();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    public String getFirstName() {
        return firstName.firstName();
    }
    public String getLastName() {
        return lastName.lastName();
    }
    public Long getId() {
        return id;
    }
    public EmailDirection getEmail() {
        return email;
    }
    public Password getPassword() {
        return password;
    }
}
