package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.valueobjects.UserId;


public record CreateQuestionResource(String category, Long userId, String question) {
}
