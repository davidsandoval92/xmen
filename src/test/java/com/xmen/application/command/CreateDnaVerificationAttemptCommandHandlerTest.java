package com.xmen.application.command;

import com.xmen.domain.aggregates.DnaVerificationAttemptAggregate;
import com.xmen.domain.repositories.DnaAttemptRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CreateDnaVerificationAttemptCommandHandlerTest {

    @MockBean
    DnaAttemptRepository dnaAttemptRepository;

    @Autowired
    CreateDnaVerificationAttemptCommandHandler handler;

    @Before
    public void setup() {
        dnaAttemptRepository = mock(DnaAttemptRepository.class);
        handler = new CreateDnaVerificationAttemptCommandHandler(dnaAttemptRepository);
    }


    /**
     * Given a command  CreateDnaVerificationAttemptCommand
     * When handle method id called
     * Then handles should use repository and persist the information
     */
    @Test
    public void handle_withSuccessFlow() throws Exception {

        DnaVerificationAttemptAggregate aggregate = new DnaVerificationAttemptAggregate(true);

        handler.handle(CreateDnaVerificationAttemptCommand.create(aggregate));

        verify(dnaAttemptRepository).registerAttempt(Mockito.any());

    }
}
