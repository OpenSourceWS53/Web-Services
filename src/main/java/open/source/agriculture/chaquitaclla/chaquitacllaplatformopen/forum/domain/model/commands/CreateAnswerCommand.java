package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.valueobjects.UserId;

public record CreateAnswerCommand(UserId userId, Question question, String answer) {
}
