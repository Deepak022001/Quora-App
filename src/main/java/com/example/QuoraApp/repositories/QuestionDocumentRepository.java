package com.example.QuoraApp.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.example.QuoraApp.models.QuestionElasticDocument;

public interface QuestionDocumentRepository extends ElasticsearchRepository<QuestionElasticDocument, String> {
    List<QuestionElasticDocument> findByTitleContainingOrContentContaining(String title, String content);
}
