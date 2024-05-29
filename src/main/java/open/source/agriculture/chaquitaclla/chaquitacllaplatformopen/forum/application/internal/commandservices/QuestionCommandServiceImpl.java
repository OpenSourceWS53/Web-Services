package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.application.internal.commandservices;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateQuestionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.DeleteQuestionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.UpdateQuestionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services.QuestionCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.infrastructure.persistence.jpa.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionCommandServiceImpl implements QuestionCommandService {

    private final QuestionRepository questionRepository;

    public QuestionCommandServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    @Override
    public Long handle(CreateQuestionCommand command) {
        var question = new Question(command.category(), command.userId(),command.question());
        questionRepository.save(question);
        return question.getId();
    }

    @Override
    public Optional<Question> handle(UpdateQuestionCommand command) {
        if(!questionRepository.existsById(command.questionId())){
            throw new IllegalArgumentException("Question does not exist");
        }
        var questionToUpdate = questionRepository.findById(command.questionId()).get();
        var updateQuestion = questionRepository.save(questionToUpdate.updateInformation(command.category(),command.question()));
        return Optional.of(updateQuestion);
    }

    @Override
    public void handle(DeleteQuestionCommand command) {
        if(!questionRepository.existsById(command.questionId())){
            throw new IllegalArgumentException("Question does not exist");
        }
        questionRepository.deleteById(command.questionId());
    }
}
