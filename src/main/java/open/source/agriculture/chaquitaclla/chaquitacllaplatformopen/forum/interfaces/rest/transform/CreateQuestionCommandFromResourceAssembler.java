package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateQuestionCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.CreateQuestionResource;

public class CreateQuestionCommandFromResourceAssembler {
    public static CreateQuestionCommand toCommandFromResource(Long userId,CreateQuestionResource resource){
        return new CreateQuestionCommand(
                resource.category(),
                userId,
                resource.questionText()
        );
    }
}
