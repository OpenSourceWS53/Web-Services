package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.valueobjects.NameUserRecord;
import org.springframework.data.annotation.CreatedDate;
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
    private NameUserRecord name;



    @Embedded
    @Email
    @AttributeOverride(name = "emialDirection",column = @Column(name = "email"))
    private EmailDirection email;

    @Embedded
    @AttributeOverride(name = "password",column = @Column(name = "password"))
    private Password password;


    @ManyToOne
    private City city;


    @ManyToOne
    private Subscription subscription;
    @CreatedDate
    private Date star_date;
    @CreatedDate
    private Date end_date;


    public User() {
    }

    public User(String firstName,String  lastName, String email, String password) {
        this.name = new NameUserRecord(firstName,lastName);
        this.email = new EmailDirection(email);
        this.password = new Password(password);
    }
    
    public Long getId() {
        return id;
    }
    public void updateName(String firstName, String lastName) {
        this.name = new NameUserRecord(firstName, lastName);
    }
    public String getFullName(){
        return this.name.fullName();
    }

    public Date getStar_date() {
        return star_date;
    }
    public Date getEnd_date() {
        return end_date;
    }

}
