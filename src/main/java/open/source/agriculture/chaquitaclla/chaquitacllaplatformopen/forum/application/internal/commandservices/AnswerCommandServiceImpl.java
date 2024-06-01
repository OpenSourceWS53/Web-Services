package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateAnswerCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Answer;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.DeleteAnswerCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.UpdateAnswerCommand;
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

    @Override
    public Optional<Answer> handle(UpdateAnswerCommand command) {
        if(!answerRepository.existsById(command.answerId()))
            throw new IllegalArgumentException("Answer does not exist");
        var answerToUpdate = answerRepository.findById(command.answerId()).get();
        var updateAnswer = answerRepository.save(answerToUpdate.updateInformation(command.answer()));
        return Optional.of(updateAnswer);
    }

    @Override
    public void handle(DeleteAnswerCommand command) {
        if(!answerRepository.existsById(command.answerId()))
            throw new IllegalArgumentException("Answer does not exist");
        answerRepository.deleteById(command.answerId());
    }
}
