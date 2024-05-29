package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.users.model.aggregates.User;

public record CreateQuestionCommand(String category, User user, String question) {
}
