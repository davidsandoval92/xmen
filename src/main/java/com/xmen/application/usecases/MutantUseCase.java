package com.xmen.application.usecases;


import com.xmen.application.command.CreateDnaVerificationAttemptCommand;
import com.xmen.application.cqrs.commandbus.CommandBus;
import com.xmen.application.cqrs.querybus.QueryBus;
import com.xmen.application.query.GetDnaVerificationAttemptQuery;
import com.xmen.domain.aggregates.DnaAggregate;
import com.xmen.domain.aggregates.VerificationAttemptAggregate;
import com.xmen.domain.vo.Verification;

/**
 * The use case for Mutants
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public class MutantUseCase {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public MutantUseCase(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    public Verification validateAttempts() throws Exception {
        return VerificationAttemptAggregate.validateAttempts(queryBus.handle(new GetDnaVerificationAttemptQuery()));
    }

    public boolean isMutant(String[] dna) throws Exception {
        boolean isMutant = DnaAggregate.isMutant(dna);
        CreateDnaVerificationAttemptCommand command = CreateDnaVerificationAttemptCommand.create(new VerificationAttemptAggregate(isMutant));
        commandBus.handle(command);
        return isMutant;
    }

}
