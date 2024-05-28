package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.entities.Answer;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllAnswersByQuestionIdAndUserIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllAnswersByQuestionIdQuery;

import java.util.List;

public interface AnswerQueryService {
    List<Answer> handle(GetAllAnswersByQuestionIdQuery query);
    List<Answer> handle(GetAllAnswersByQuestionIdAndUserIdQuery query);
}
