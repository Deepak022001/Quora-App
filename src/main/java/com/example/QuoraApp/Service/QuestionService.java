package com.example.QuoraApp.Service;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.springframework.data.domain.Pageable;
import org.springframework.cglib.core.Local;
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
import com.example.QuoraApp.utils.CursorUtils;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {

    private final QuestionRepository questionRepository;

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
    public Flux<QuestionResponseDto> searchQuestion(String searchTerm, int offset, int page) {
        return questionRepository.findByTitleOrContainingIgnoreCase(searchTerm,PageRequest.of(offset, page))
        .map(QuestionAdapter::toQuestionResponseDto)
        .doOnError(error->System.out.println("Error Searchign term"+error))
        .doOnComplete(() ->
    System.out.println("Questions searched successfully"));
    }   
    @Override
    public Flux<QuestionResponseDto>getAllQuestions(String cursor,int size){
        Pageable pageable=PageRequest.of(0,size);
        if(!CursorUtils.isValidCursor(cursor)){
            return questionRepository.findTop10ByOrderByCreatedAtAsc()
            .map(QuestionAdapter::toQuestionResponseDto)
            .take(3)
            .doOnError(error->System.out.println("Error Fetching question"+error))
            .doOnComplete(()->System.out.println("Successfully fetched "));
        }else{
            // Frontend sends:
            // "2026-06-05T10:15:30"(String)
            // Backend converts:
            // LocalDateTime.parse(cursor)
            // into:
            // 2026-06-05T10:15:30
            LocalDateTime cursorTimeStamp=CursorUtils.parseCursor(cursor);
            return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimeStamp,pageable)
            .map(QuestionAdapter::toQuestionResponseDto)
            .doOnError(error->System.out.println("Error fetching question"+error))
            .doOnComplete(()->System.out.println("Successfully fetched"));
        }
    }
}
// 33:05