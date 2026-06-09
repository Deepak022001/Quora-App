package com.example.QuoraApp.Service;

import org.springframework.stereotype.Service;

import com.example.QuoraApp.Service.Interfaces.IQuestionIndexService;
import com.example.QuoraApp.models.Question;
import com.example.QuoraApp.models.QuestionElasticDocument;
import com.example.QuoraApp.repositories.QuestionDocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionIndexService implements IQuestionIndexService {
    private final QuestionDocumentRepository questionDocumentRepository;

    @Override
    public void createQuesitionIndex(Question question) {
        QuestionElasticDocument qDocument = QuestionElasticDocument.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .build();
        questionDocumentRepository.save(qDocument);
    }

}
