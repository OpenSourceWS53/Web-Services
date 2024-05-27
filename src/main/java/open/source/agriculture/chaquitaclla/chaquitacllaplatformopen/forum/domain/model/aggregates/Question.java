package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.model.aggregates.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Entity(name = "questions")
public class Question extends AbstractAggregateRoot<Question> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private String question;


    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Question() {
        this.category = Strings.EMPTY;
        this.user = new User();
        this.question = Strings.EMPTY;
    }

    public Question(String category, User user, String question) {
        this();
        this.category = category;
        this.user = user;
        this.question = question;
    }
}
