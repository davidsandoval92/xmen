package com.xmen.application.usecases;


import com.xmen.domain.aggregates.DnaAggregate;
import com.xmen.domain.aggregates.VerificationAggregate;
import com.xmen.domain.repositories.AttemptRepository;
import com.xmen.domain.vo.Verification;
import com.xmen.domain.entities.VerificationAttempt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * The use case for Mutants
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public class MutantUseCase {

    private final AttemptRepository repository;

    public MutantUseCase(AttemptRepository repository) {
        this.repository = repository;
    }

    public Verification validateAttempts() {
        return VerificationAggregate.validateAttempts(repository.attempts());
    }

    public boolean isMutant(String[] dna) {
        boolean isMutant = DnaAggregate.isMutant(dna);
        repository.registerAttempt(new VerificationAggregate(isMutant));
        return isMutant;
    }

}
