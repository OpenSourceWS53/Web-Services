package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.aggregates.Question;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.valueobjects.UserId;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.domain.model.aggregates.User;

public record CreateAnswerCommand(UserId userId, Question question, String answer) {
}
