package com.example.QuoraApp.Service.Interfaces;

import com.example.QuoraApp.Dto.AnswerRequestDto;
import com.example.QuoraApp.Dto.AnswerResponseDto;

import reactor.core.publisher.Mono;

public interface IAnswerService {
       public Mono<AnswerResponseDto> createAnswer(AnswerRequestDto answerRequestDto);

       public Mono<AnswerResponseDto> getAnswerById(String Id);
}