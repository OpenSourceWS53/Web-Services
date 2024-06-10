package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.City;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.NameUserRecord;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.EmailDirection;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.Password;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User extends AbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private NameUserRecord name;

    @Embedded
    @Email
    @AttributeOverride(name = "emialDirection", column = @Column(name = "email"))
    public EmailDirection email;

    @Embedded
    @AttributeOverride(name = "password", column = @Column(name = "password"))
    private Password password;

    @ManyToOne
    private City city;

    @ManyToOne
    private Subscription subscription;

    @CreatedDate
    private Date start_date;

    @CreatedDate
    private Date end_date;

    public User() {}

    public User(String firstName, String lastName, String email, String password) {
        this.name = new NameUserRecord(firstName, lastName);
        this.email = new EmailDirection(email);
        this.password = new Password(password);
    }

    public Long getId() {
        return id;
    }

    public NameUserRecord getName() {
        return name;
    }

    public EmailDirection getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public City getCity() {
        return city;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public Long getCityId() {
        return city != null ? city.getId() : null;
    }

    public Long getSubscriptionId() {
        return subscription != null ? subscription.getId() : null;
    }
}