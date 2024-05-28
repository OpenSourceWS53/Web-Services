package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.application.internal.queryservices;


import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.entities.Answer;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllAnswersByQuestionIdAndUserIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllAnswersByQuestionIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services.AnswerQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.infrastructure.persistence.jpa.repositories.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerQueryServiceImpl implements AnswerQueryService {

    private final AnswerRepository answerRepository;

    public AnswerQueryServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Answer> handle(GetAllAnswersByQuestionIdQuery query) {
       return answerRepository.findByQuestionId(query.questionId());
    }

    @Override
    public List<Answer> handle(GetAllAnswersByQuestionIdAndUserIdQuery query) {
       return answerRepository.findByQuestionIdAndUserId(query.questionId(), query.userId());
    }
}
