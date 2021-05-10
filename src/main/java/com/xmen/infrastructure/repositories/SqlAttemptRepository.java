package com.xmen.infrastructure.repositories;

import com.xmen.domain.aggregates.VerificationAggregate;
import com.xmen.domain.repositories.AttemptRepository;
import com.xmen.infrastructure.repositories.models.VerificationAttempt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SqlAttemptRepository implements AttemptRepository {

    private final JpaAttemptRepositorySql repository;

    public SqlAttemptRepository(JpaAttemptRepositorySql repository){
        this.repository = repository;
    }

    @Override
    public void registerAttempt(VerificationAggregate attempt) {
        repository.save(new VerificationAttempt(
                attempt.getExamResult(),
                attempt.getExamDate()
        ));
    }

    @Override
    public List<com.xmen.domain.entities.VerificationAttempt> attempts() {
        log.info("Getting validation attempts.");
        return repository.findAll()
                .stream()
                .map(attempt -> new com.xmen.domain.entities.VerificationAttempt(
                        attempt.getId(),
                        attempt.getExamResult(),
                        attempt.getExamDate()
                ))
                .collect(Collectors.toList());
    }
}