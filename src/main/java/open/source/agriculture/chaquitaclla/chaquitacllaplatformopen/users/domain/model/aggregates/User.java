package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates;

import jakarta.persistence.*;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.entities.City;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.NameUserRecord;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.EmailDirection;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.Password;
import java.util.Date;

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

    private EmailDirection email;

    @Embedded
    @AttributeOverride(name = "password", column = @Column(name = "password"))
    private Password password;

    @ManyToOne
    private City city;

    @ManyToOne
    private Subscription subscription;

    @CreatedDate
    private Date startDate;

    @CreatedDate
    private Date endDate;

    public User() {}

    public User(String firstName, String lastName, EmailDirection email, String password) {
        this.name = new NameUserRecord(firstName, lastName);
        this.email = email;
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Long getCityId() {
        return city != null ? city.getId() : null;
    }

    public Long getSubscriptionId() {
        return subscription != null ? subscription.getId() : null;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
