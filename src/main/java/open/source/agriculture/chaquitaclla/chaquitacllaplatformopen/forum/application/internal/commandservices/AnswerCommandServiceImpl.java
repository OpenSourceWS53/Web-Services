package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateAnswerCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Answer;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services.AnswerCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.infrastructure.persistence.jpa.repositories.AnswerRepository;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.infrastructure.persistence.jpa.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerCommandServiceImpl implements AnswerCommandService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    public AnswerCommandServiceImpl(AnswerRepository answerRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }


    @Override
    public Long handle(CreateAnswerCommand command) {
        Optional<Question> optionalQuestion = questionRepository.findById(command.questionId());
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            Answer answer = new Answer(command.userId(), question, command.answer());
            answerRepository.save(answer);
            return answer.getId();
        } else {
            throw new IllegalArgumentException("Question does not exist");
        }
    }
}
