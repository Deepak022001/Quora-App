package com.example.QuoraApp.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.QuoraApp.models.Answer;

public interface AnswerRepository extends ReactiveMongoRepository<Answer,String>{
    
}
