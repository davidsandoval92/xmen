package com.xmen.infrastructure.repositories;

import com.xmen.domain.aggregates.DnaVerificationAttemptAggregate;
import com.xmen.domain.entities.DnaVerificationAttempt;
import com.xmen.infrastructure.repositories.models.VerificationAttempt;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Before;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class SqlDnaAttemptRepositoryTest {

    @MockBean
    private JpaAttemptRepositorySql repository;

    @Autowired
    private SqlDnaAttemptRepository sqlDnaAttemptRepository;

    @Before
    public void setup() {
        repository = mock(JpaAttemptRepositorySql.class);
        sqlDnaAttemptRepository = new SqlDnaAttemptRepository(repository);
    }

    /**
     * Given a consult dna attempts
     * When getAttempts method id called
     * Then list of VerificationAttempt should be returned
     */
    @Test
    public void getAttempts_withSuccessFlow(){

        final LocalDate dateAttempt = LocalDate.now();

        final List<VerificationAttempt> attempts = Arrays.asList(VerificationAttempt.builder()
                .id(1)
                .examResult("MUTANT")
                .examDate(dateAttempt)
                .build());

        when(repository.findAll()).thenReturn(attempts);

        List<DnaVerificationAttempt> attemptsResult = sqlDnaAttemptRepository.getAttempts();

        assertThat(attemptsResult.size(), is(equalTo(1)));
        assertThat(attemptsResult.get(0).getId(), is(equalTo(1L)));
        assertThat(attemptsResult.get(0).getExamResult(), is(equalTo("MUTANT")));
        assertThat(attemptsResult.get(0).getExamDate(), is(equalTo(dateAttempt)));

    }

    /**
     * Given a save dna attempts
     * When registerAttempt method id called
     * Then a save is persisted
     */
    @Test
    public void registerAttempt_withSuccessFlow(){

        final DnaVerificationAttemptAggregate aggregate = new DnaVerificationAttemptAggregate(true);

        when(repository.save(Mockito.any())).thenReturn(Mockito.any());

        sqlDnaAttemptRepository.registerAttempt(aggregate);

        verify(repository).save(Mockito.any());

    }
}

