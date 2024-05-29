package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.valueobjects.UserId;

public record QuestionResource(Long id, String Category, UserId userId, String question) {
}
