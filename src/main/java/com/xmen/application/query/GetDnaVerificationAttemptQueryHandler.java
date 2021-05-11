package com.xmen.application.query;

import com.xmen.application.cqrs.querybus.QueryHandler;
import com.xmen.domain.entities.VerificationAttempt;
import com.xmen.domain.repositories.AttemptRepository;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Allow to manage the execute dna verified attempt query
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
@Component
public class GetDnaVerificationAttemptQueryHandler implements QueryHandler<List<VerificationAttempt>, GetDnaVerificationAttemptQuery> {

    private AttemptRepository attemptRepository;

    public GetDnaVerificationAttemptQueryHandler(AttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }

    @Override
    public List<VerificationAttempt> handle(GetDnaVerificationAttemptQuery query) throws Exception {
        return attemptRepository.attempts();
    }
}
