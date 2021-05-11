package com.xmen.application.query;

import com.xmen.domain.entities.DnaVerificationAttempt;
import com.xmen.domain.repositories.DnaAttemptRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GetDnaVerificationAttemptQueryHandlerTest {


    @MockBean
    DnaAttemptRepository dnaAttemptRepository;

    @Autowired
    GetDnaVerificationAttemptQueryHandler handler;

    @Before
    public void setup() {
        dnaAttemptRepository = mock(DnaAttemptRepository.class);
        handler = new GetDnaVerificationAttemptQueryHandler(dnaAttemptRepository);
    }


    /**
     * Given a command  GetDnaVerificationAttemptQuery
     * When handle method id called
     * Then handles should use repository and consult the information
     */
    @Test
    public void handle_withSuccessFlow() throws Exception {

        final LocalDate examDate = LocalDate.now();

        List<DnaVerificationAttempt> attemptsRecovered = Arrays.asList(
                new DnaVerificationAttempt(1, "MUTANT", examDate));
        GetDnaVerificationAttemptQuery query = new GetDnaVerificationAttemptQuery();
        when(dnaAttemptRepository.getAttempts()).thenReturn(attemptsRecovered);

        List<DnaVerificationAttempt> attempts = handler.handle(query);

        assertThat(attempts.size(), is(equalTo(1)));
        assertThat(attempts.get(0).getId(), is(equalTo(1L)));
        assertThat(attempts.get(0).getExamResult(), is(equalTo("MUTANT")));
        assertThat(attempts.get(0).getExamDate(), is(equalTo(examDate)));
        verify(dnaAttemptRepository).getAttempts();

    }
}
