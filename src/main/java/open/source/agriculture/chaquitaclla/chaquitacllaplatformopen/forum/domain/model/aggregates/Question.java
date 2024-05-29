package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.valueobjects.UserId;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "questions")
public class Question extends AbstractAggregateRoot<Question> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String category;


    @JoinColumn(name = "user_id")
    private UserId userId;

    @NotNull
    private String question;


    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Question() {
        this.category = Strings.EMPTY;
        this.userId = new UserId();
        this.question = Strings.EMPTY;
    }

    public Question(String category, UserId userId, String question) {
        this();
        this.category = category;
        this.userId = userId;
        this.question = question;
    }

    public Question updateInformation(String category, String question) {
        this.category = category;
        this.question = question;
        return this;
    }
}
