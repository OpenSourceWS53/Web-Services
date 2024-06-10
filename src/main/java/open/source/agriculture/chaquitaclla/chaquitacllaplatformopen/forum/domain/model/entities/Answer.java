package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.entities;

import jakarta.persistence.*;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.shared.domain.model.entities.AuditableModel;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity(name = "answers")
public class Answer extends AuditableModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    private String answer;

    public Answer(){
        this.user = new User();
        this.question = new Question();
        this.answer = Strings.EMPTY;
    }

    public Answer(User user, Question question, String answer){
        this();
        this.user = user;
        this.question = question;
        this.answer = answer;
    }
}
