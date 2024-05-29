package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.valueobjects.UserId;


public record CreateQuestionCommand(String category, UserId userId, String question) {
}
