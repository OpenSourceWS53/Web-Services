package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateAnswerCommand;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.CreateAnswerResource;

public class CreateAnswerCommandFromResourceAssembler {
    public static CreateAnswerCommand toCommandFromResource(Long userId, Long questionId,CreateAnswerResource resource){
        return new CreateAnswerCommand(
                userId,
                questionId,
                resource.answerText()
        );

    }
}
