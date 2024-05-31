package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;



public record CreateAnswerCommand(Long userId, Long questionId, String answer) {
}
