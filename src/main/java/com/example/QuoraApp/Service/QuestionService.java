package com.example.QuoraApp.Service;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.QuoraApp.Dto.QuestionRequestDto;
import com.example.QuoraApp.Dto.QuestionResponseDto;
import com.example.QuoraApp.adapter.QuestionAdapter;
import com.example.QuoraApp.models.Question;
import com.example.QuoraApp.repositories.QuestionRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<QuestionResponseDto> createQuestion(QuestionRequestDto questionRequestDto) {
        Question question=Question.builder()
        .title(questionRequestDto.getTitle())
        .content(questionRequestDto.getContent())
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
        return questionRepository.save(question).map(QuestionAdapter::toQuestionResponseDto)
        .doOnSuccess(response->System.out.println("Question created successfully:"+response))
        .doOnError(error->System.out.println("Error creating quesiton :"+error));
    }

    @Override
    public Flux<QuestionResponseDto> searchQuestion(String searchTerm, int page, int size) {
        String escapedSearchTerm = Pattern.quote(searchTerm.trim());
        Criteria titleCriteria = Criteria.where("title").regex(escapedSearchTerm, "i");
        Criteria contentCriteria = Criteria.where("content").regex(escapedSearchTerm, "i");
        Query query = new Query(new Criteria().orOperator(titleCriteria, contentCriteria))
        .with(PageRequest.of(page,size));

        return mongoTemplate.find(query, Question.class)
        .map(QuestionAdapter::toQuestionResponseDto)
        .doOnError(error->System.out.println("Error searching the question"+error))
        .doOnComplete(()->System.out.println("Question searched successfully"));
    }   
}
