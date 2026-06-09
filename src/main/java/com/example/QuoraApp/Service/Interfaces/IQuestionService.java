package com.example.QuoraApp.Service.Interfaces;

import java.util.List;

import com.example.QuoraApp.Dto.QuestionRequestDto;
import com.example.QuoraApp.Dto.QuestionResponseDto;
import com.example.QuoraApp.models.QuestionElasticDocument;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IQuestionService {
    public Mono<QuestionResponseDto> createQuestion(QuestionRequestDto questionRequestDto);

    public Flux<QuestionResponseDto> searchQuestion(String searchTerm, int page, int size);

    public Flux<QuestionResponseDto> getAllQuestions(String cursor, int size);

    public Mono<QuestionResponseDto> getQuestionById(String id);

    public List<QuestionElasticDocument> searchQuestionByElasticsearch(String query);
}
