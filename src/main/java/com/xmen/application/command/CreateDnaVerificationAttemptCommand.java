package com.xmen.application.command;

import com.xmen.application.cqrs.commandbus.Command;
import com.xmen.domain.aggregates.DnaVerificationAttemptAggregate;

/**
 * Create dna verified attempt command
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public class CreateDnaVerificationAttemptCommand extends Command {

    private DnaVerificationAttemptAggregate dnaVerificationAttemptAggregate;

    public CreateDnaVerificationAttemptCommand(DnaVerificationAttemptAggregate dnaVerificationAttemptAggregate) {
        this.dnaVerificationAttemptAggregate = dnaVerificationAttemptAggregate;
    }

    public static CreateDnaVerificationAttemptCommand create(DnaVerificationAttemptAggregate dnaVerificationAttemptAggregate){
        return  new CreateDnaVerificationAttemptCommand(dnaVerificationAttemptAggregate);
    }

    public DnaVerificationAttemptAggregate getVerificationAggregate() {
        return dnaVerificationAttemptAggregate;
    }

}
