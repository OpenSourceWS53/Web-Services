package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.valueobjects.UserId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.entities.AuditableModel;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "answers")
public class Answer extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "user_id")
    private UserId userId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String answerText;

    public Answer(){
        this.userId = new UserId();
        this.question = new Question();
        this.answerText = Strings.EMPTY;
    }

    public Answer(Long userId, Question question, String answerText){
        this();
        this.userId = new UserId(userId);
        this.question = question;
        this.answerText = answerText;
    }

    public Answer updateInformation(String answer){
        this.answerText = answer;
        return this;
    }
}
