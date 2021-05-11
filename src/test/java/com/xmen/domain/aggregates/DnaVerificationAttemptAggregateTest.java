package com.xmen.domain.aggregates;

import com.xmen.domain.entities.DnaVerificationAttempt;
import com.xmen.domain.vo.DnaVerification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class DnaVerificationAttemptAggregateTest {

    /**
     * Consulted an DnaVerificationAttempt with mutants and humans results
     * When validateAttempts method id called
     * Then DnaVerification should be returned with average
     */
    @Test
    public void verifyAttemptsJoined_withSuccessFlow(){
        LocalDate examDate = LocalDate.now();
        List<DnaVerificationAttempt> dnaVerificationAttempts = Arrays.asList(
                new DnaVerificationAttempt(1, DnaVerificationAttemptAggregate.MUTANT, examDate),
                new DnaVerificationAttempt(1, DnaVerificationAttemptAggregate.MUTANT, examDate),
                new DnaVerificationAttempt(1, DnaVerificationAttemptAggregate.MUTANT, examDate),
                new DnaVerificationAttempt(1, DnaVerificationAttemptAggregate.NO_MUTANT, examDate),
                new DnaVerificationAttempt(1, DnaVerificationAttemptAggregate.NO_MUTANT, examDate));


        final DnaVerification verification = DnaVerificationAttemptAggregate.validateAttempts(dnaVerificationAttempts);

        assertThat(verification.getCountMutantDna(), is(equalTo(3L)));
        assertThat(verification.getCountHumanDna(), is(equalTo(2L)));
        assertThat(verification.getRatio().toString(), is(equalTo("1.5000")));

    }

    /**
     * Consulted an DnaVerificationAttempt only humans results
     * When validateAttempts method id called
     * Then DnaVerification should be returned with average
     */
    @Test
    public void verifyAttemptsHumans_withSuccessFlow(){
        LocalDate examDate = LocalDate.now();
        List<DnaVerificationAttempt> dnaVerificationAttempts = Arrays.asList(
                new DnaVerificationAttempt(1, DnaVerificationAttemptAggregate.NO_MUTANT, examDate),
                new DnaVerificationAttempt(1, DnaVerificationAttemptAggregate.NO_MUTANT, examDate));


        final DnaVerification verification = DnaVerificationAttemptAggregate.validateAttempts(dnaVerificationAttempts);

        assertThat(verification.getCountMutantDna(), is(equalTo(0L)));
        assertThat(verification.getCountHumanDna(), is(equalTo(2L)));
        assertThat(verification.getRatio().toString(), is(equalTo("0.0000")));

    }


    /**
     * Consulted an DnaVerificationAttempt only mutants results
     * When validateAttempts method id called
     * Then DnaVerification should be returned with average
     */
    @Test
    public void verifyAttemptsMutants_withSuccessFlow(){
        LocalDate examDate = LocalDate.now();
        List<DnaVerificationAttempt> dnaVerificationAttempts = Arrays.asList(
                new DnaVerificationAttempt(1, DnaVerificationAttemptAggregate.MUTANT, examDate),
                new DnaVerificationAttempt(1, DnaVerificationAttemptAggregate.MUTANT, examDate),
                new DnaVerificationAttempt(1, DnaVerificationAttemptAggregate.MUTANT, examDate));


        final DnaVerification verification = DnaVerificationAttemptAggregate.validateAttempts(dnaVerificationAttempts);

        assertThat(verification.getCountMutantDna(), is(equalTo(3L)));
        assertThat(verification.getCountHumanDna(), is(equalTo(0L)));
        assertThat(verification.getRatio().toString(), is(equalTo("3.0000")));

    }
}
