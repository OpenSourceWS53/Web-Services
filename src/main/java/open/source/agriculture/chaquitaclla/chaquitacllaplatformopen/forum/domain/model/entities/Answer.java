package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.valueobjects.UserId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.entities.AuditableModel;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity(name = "answers")
@Getter
public class Answer extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JoinColumn(name = "user_id")
    private UserId userId;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String answer;

    public Answer(){
        this.userId = new UserId();
        this.question = new Question();
        this.answer = Strings.EMPTY;
    }

    public Answer(UserId userId, Question question, String answer){
        this();
        this.userId = userId;
        this.question = question;
        this.answer = answer;
    }
}
