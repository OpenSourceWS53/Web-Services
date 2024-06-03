package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;

public record AnswerResource(Long id, Long userId, Long questionId, String answer) {
}
