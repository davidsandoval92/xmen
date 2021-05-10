package com.xmen.domain.repositories;

import com.xmen.domain.aggregates.VerificationAggregate;
import com.xmen.domain.entities.VerificationAttempt;

import java.util.List;

public interface AttemptRepository {

    void registerAttempt(VerificationAggregate attempt);

    List<VerificationAttempt> attempts();
}
