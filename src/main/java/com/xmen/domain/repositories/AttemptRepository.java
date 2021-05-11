package com.xmen.domain.repositories;

import com.xmen.domain.aggregates.VerificationAttemptAggregate;
import com.xmen.domain.entities.VerificationAttempt;

import java.util.List;

/**
 * Port exposes methods to manage attempts
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public interface AttemptRepository {

    /**
     * Register an validate DNA attempt
     *
     * @param attempt
     */
    void registerAttempt(VerificationAttemptAggregate attempt);

    /**
     *
     * @return list of attempts validated
     */
    List<VerificationAttempt> attempts();
}
