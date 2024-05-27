package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllAnswersByQuestionIdAndUserIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllAnswersByQuestionIdQuery;

import java.util.List;

public interface AnswerQueryService {
    List<Question> handle(GetAllAnswersByQuestionIdQuery query);
    List<Question> handle(GetAllAnswersByQuestionIdAndUserIdQuery query);
}
