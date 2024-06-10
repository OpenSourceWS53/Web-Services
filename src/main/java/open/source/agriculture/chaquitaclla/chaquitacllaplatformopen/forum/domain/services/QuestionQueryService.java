package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllQuestionsByUserIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllQuestionsQuery;

import java.util.List;

public interface QuestionQueryService {
    List<Question> handle(GetAllQuestionsQuery query);
    List<Question> handle(GetAllQuestionsByUserIdQuery query);

}
