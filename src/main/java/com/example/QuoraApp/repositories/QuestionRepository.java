package com.example.QuoraApp.repositories;

import org.springframework.stereotype.Repository;

import com.example.QuoraApp.models.Question;

import reactor.core.publisher.Flux;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question,String>{   
    @Query("{'$or':[{'title':{'$regex':?0,'$options':'i'}},{'content':{'$regex':?0,'$options':'i'}}]}")
    Flux<Question> searchByTitleOrContent(String searchTerm, Pageable pageable);
}
