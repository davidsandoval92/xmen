package com.xmen.application.command;

import com.xmen.application.cqrs.commandbus.CommandHandler;
import com.xmen.domain.repositories.AttemptRepository;
import org.springframework.stereotype.Component;

/**
 * Allow to manage the execute dna verified attempt command
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
@Component
public class CreateDnaVerificationAttemptCommandHandler implements CommandHandler<CreateDnaVerificationAttemptCommand> {

    private AttemptRepository attemptRepository;

    public CreateDnaVerificationAttemptCommandHandler(AttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }

    @Override
    public void handle(CreateDnaVerificationAttemptCommand command) throws Exception {
        attemptRepository.registerAttempt(command.getVerificationAggregate());
    }
}