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
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Question extends AbstractAggregateRoot<Question> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String category;


    @JoinColumn(name = "user_id")
    private UserId userId;

    @NotNull
    private String questionText;


    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public Question() {
        this.category = Strings.EMPTY;
        this.userId = new UserId();
        this.questionText = Strings.EMPTY;
    }

    public Question(String category, Long userId, String questionText) {
        this();
        this.category = category;
        this.userId = new UserId(userId);
        this.questionText = questionText;
    }

    public Question updateInformation(String category, String question) {
        this.category = category;
        this.questionText = question;
        return this;
    }
}
