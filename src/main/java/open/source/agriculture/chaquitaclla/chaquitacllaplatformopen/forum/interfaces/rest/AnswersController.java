package open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest;

import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAllAnswersByQuestionIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.model.queries.GetAnswerByIdQuery;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services.AnswerCommandService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.domain.services.AnswerQueryService;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.AnswerResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.resources.CreateAnswerResource;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform.AnswerResourceFromEntityAssembler;
import open.source.agriculture.chaquitaclla.chaquitacllaplatformopen.forum.interfaces.rest.transform.CreateAnswerCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/answers")
public class AnswersController {
    private final AnswerCommandService answerCommandService;
    private final AnswerQueryService answerQueryService;

    public AnswersController(AnswerCommandService answerCommandService, AnswerQueryService answerQueryService) {
        this.answerCommandService = answerCommandService;
        this.answerQueryService = answerQueryService;
    }

    @PostMapping
    public ResponseEntity<AnswerResource> createAnswer(@RequestBody CreateAnswerResource resource){
        var createAnswerCommand = CreateAnswerCommandFromResourceAssembler.toCommandFromResource(resource);
        var answerId = answerCommandService.handle(createAnswerCommand);
        if(answerId == 0L) return ResponseEntity.badRequest().build();
        var getAnswerByIdQuery = new GetAnswerByIdQuery(answerId);
        var answer = answerQueryService.handle(getAnswerByIdQuery);
        if(answer.isEmpty()) return ResponseEntity.badRequest().build();
        var answerResource =  AnswerResourceFromEntityAssembler.toResourceFromEntity(answer.get());
        return ResponseEntity.ok(answerResource);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<AnswerResource>> getAllAnswersByQuestionId(@PathVariable Long questionId){
        var getAnswersByQuestionIdQuery = new GetAllAnswersByQuestionIdQuery(questionId);
        var answers = answerQueryService.handle(getAnswersByQuestionIdQuery);
        var answerResource = answers.stream()
                .map(AnswerResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(answerResource);
    }
}
