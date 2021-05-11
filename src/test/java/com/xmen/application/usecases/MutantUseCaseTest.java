package com.xmen.application.usecases;

import com.xmen.application.cqrs.commandbus.CommandBus;
import com.xmen.application.cqrs.querybus.QueryBus;
import com.xmen.domain.entities.DnaVerificationAttempt;
import com.xmen.domain.vo.DnaVerification;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MutantUseCaseTest {

    @MockBean
    private CommandBus commandBus;

    @MockBean
    private QueryBus queryBus;

    @Autowired
    private MutantUseCase mutantUseCase;

    @Before
    public void setup() {
        commandBus = mock(CommandBus.class);
        queryBus = mock(QueryBus.class);
        mutantUseCase = new MutantUseCase(commandBus, queryBus);
    }

    /**
     * Given a consult dna attempts
     * When validateAttempts method id called
     * Then DnaVerification should be returned with the averages
     */
    @Test
    public void validateAttempts_withSuccessFlow() throws Exception {

        LocalDate date = LocalDate.now();
        List<DnaVerificationAttempt> dnaVerificationAttempts = Arrays.asList(
                new DnaVerificationAttempt(1,"MUTANT", date),
                new DnaVerificationAttempt(2,"NO-MUTANT", date),
                new DnaVerificationAttempt(3,"MUTANT", date)
        );

        when(queryBus.handle(Mockito.any())).thenReturn(dnaVerificationAttempts);

        DnaVerification dnaVerification = mutantUseCase.validateAttempts();

        assertThat(dnaVerification.getCountMutantDna(), is(equalTo(2L)));
        assertThat(dnaVerification.getCountHumanDna(), is(equalTo(1L)));
        assertThat(dnaVerification.getRatio().toString(), is(equalTo("2.0000")));

    }

    /**
     * Given dna sample with mutant dna
     * When isMutant method id called
     * Then boolean true should be returned
     */
    @Test
    public void isMutantTrue_withSuccessFlow() throws Exception {

        String[] dna = {"AAAAGA","ATCCAA","GAAGGT","AAATGG","CATCTA","GCTACG"};

        boolean isMutant = mutantUseCase.isMutant(dna);

        assertThat(isMutant, is(equalTo(true)));
    }

    /**
     * Given dna sample
     * When isMutant method id called
     * Then boolean false should be returned
     */
    @Test
    public void isMutantFalse_withSuccessFlow() throws Exception {

        String[] dna = {"GAAAGG","ATCCAA","GAAGGA","AAATGG","CATCTA","GCTACG"};

        boolean isMutant = mutantUseCase.isMutant(dna);

        assertThat(isMutant, is(equalTo(false)));
    }
}
