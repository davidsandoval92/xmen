package com.xmen.infrastructure.repositories;

import com.xmen.domain.aggregates.DnaVerificationAttemptAggregate;
import com.xmen.domain.entities.DnaVerificationAttempt;
import com.xmen.domain.repositories.DnaAttemptRepository;
import com.xmen.infrastructure.repositories.models.VerificationAttempt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Sql repository to manage DNA attempts
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
@Slf4j
@Component
public class SqlDnaAttemptRepository implements DnaAttemptRepository {

    private final JpaAttemptRepositorySql repository;

    public SqlDnaAttemptRepository(JpaAttemptRepositorySql repository){
        this.repository = repository;
    }

    /**
     * Save an Dna verification attempt
     * @param attempt
     */
    @Override
    public void registerAttempt(DnaVerificationAttemptAggregate attempt) {
        repository.save(new VerificationAttempt(
                attempt.getExamResult(),
                attempt.getExamDate()
        ));
    }

    /**
     * Get dna verification attempts
     * @return List of DnaVerificationAttempt
     */
    @Override
    public List<DnaVerificationAttempt> getAttempts() {
        log.info("Getting validation attempts.");
        return repository.findAll()
                .stream()
                .map(attempt -> new DnaVerificationAttempt(
                        attempt.getId(),
                        attempt.getExamResult(),
                        attempt.getExamDate()
                ))
                .collect(Collectors.toList());
    }
}
