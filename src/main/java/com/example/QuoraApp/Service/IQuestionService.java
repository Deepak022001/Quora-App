package com.example.QuoraApp.Service;

import com.example.QuoraApp.Dto.QuestionRequestDto;
import com.example.QuoraApp.Dto.QuestionResponseDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {
    public Mono<QuestionResponseDto>createQuestion(QuestionRequestDto questionRequestDto);
    public Flux<QuestionResponseDto> searchQuestion(String searchTerm,int page,int size);
}
