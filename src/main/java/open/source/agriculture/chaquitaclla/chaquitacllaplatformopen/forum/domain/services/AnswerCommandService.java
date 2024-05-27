package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.commands.CreateAnswerCommand;

public interface AnswerCommandService {
    Long handle(CreateAnswerCommand command);

}
