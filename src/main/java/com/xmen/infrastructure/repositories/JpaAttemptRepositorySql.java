package com.xmen.infrastructure.repositories;

import com.xmen.infrastructure.repositories.models.VerificationAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface JpaAttemptRepositorySql extends JpaRepository<VerificationAttempt, Integer> {

    Optional<VerificationAttempt> findByIdAndExamDate(Integer id, LocalDate created);
}
