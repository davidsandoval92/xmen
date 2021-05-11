package com.xmen.application.query;

import com.xmen.application.cqrs.querybus.QueryHandler;
import com.xmen.domain.entities.DnaVerificationAttempt;
import com.xmen.domain.repositories.DnaAttemptRepository;
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
public class GetDnaVerificationAttemptQueryHandler implements QueryHandler<List<DnaVerificationAttempt>, GetDnaVerificationAttemptQuery> {

    private DnaAttemptRepository dnaAttemptRepository;

    public GetDnaVerificationAttemptQueryHandler(DnaAttemptRepository dnaAttemptRepository) {
        this.dnaAttemptRepository = dnaAttemptRepository;
    }

    @Override
    public List<DnaVerificationAttempt> handle(GetDnaVerificationAttemptQuery query) throws Exception {
        return dnaAttemptRepository.getAttempts();
    }
}
