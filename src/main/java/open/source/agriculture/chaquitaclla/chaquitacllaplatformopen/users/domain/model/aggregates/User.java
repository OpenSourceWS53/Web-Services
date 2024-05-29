package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
public class User extends AbstractAggregateRoot<User> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
