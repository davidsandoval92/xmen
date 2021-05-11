package com.xmen.application.command;

import com.xmen.application.cqrs.commandbus.Command;
import com.xmen.domain.aggregates.VerificationAttemptAggregate;

/**
 * Create dna verified attempt command
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public class CreateDnaVerificationAttemptCommand extends Command {

    private VerificationAttemptAggregate verificationAttemptAggregate;

    public CreateDnaVerificationAttemptCommand(VerificationAttemptAggregate verificationAttemptAggregate) {
        this.verificationAttemptAggregate = verificationAttemptAggregate;
    }

    public static CreateDnaVerificationAttemptCommand create(VerificationAttemptAggregate verificationAttemptAggregate){
        return  new CreateDnaVerificationAttemptCommand(verificationAttemptAggregate);
    }

    public VerificationAttemptAggregate getVerificationAggregate() {
        return verificationAttemptAggregate;
    }

}
