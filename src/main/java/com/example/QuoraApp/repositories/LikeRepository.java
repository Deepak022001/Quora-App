package com.example.QuoraApp.repositories;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.example.QuoraApp.models.Like;
public interface LikeRepository extends ReactiveMongoRepository<Like,String>{
    
    
}
