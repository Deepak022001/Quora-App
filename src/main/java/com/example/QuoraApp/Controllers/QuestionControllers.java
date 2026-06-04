package com.example.QuoraApp.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.QuoraApp.Dto.QuestionRequestDto;
import com.example.QuoraApp.Dto.QuestionResponseDto;
import com.example.QuoraApp.Service.IQuestionService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionControllers {
    private final IQuestionService questionService;
    @PostMapping("")
    public Mono<QuestionResponseDto> createQuestion(@RequestBody QuestionRequestDto questionRequestDto){        
        return questionService.createQuestion(questionRequestDto)
        .doOnSuccess(response->System.out.println("Question created successfully"+response))
        .doOnError(error->System.out.println("Error creating question:"+error));
    }
    @GetMapping("/search")
    public Flux<QuestionResponseDto>searchQuestions(@RequestParam("query") String query,@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "10")int size){
        return questionService.searchQuestion(query, page,size);
    }
}
