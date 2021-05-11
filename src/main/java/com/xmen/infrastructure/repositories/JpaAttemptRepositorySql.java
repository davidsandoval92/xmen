package com.xmen.infrastructure.repositories;

import com.xmen.infrastructure.repositories.models.VerificationAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface for SqlAttemptRepository
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public interface JpaAttemptRepositorySql extends JpaRepository<VerificationAttempt, Integer> {

}
