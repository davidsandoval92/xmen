package com.xmen.domain.aggregates;

import com.xmen.domain.entities.DnaVerificationAttempt;
import com.xmen.domain.vo.DnaVerification;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

/**
 * Verification aggregate
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public class DnaVerificationAttemptAggregate {
    final static String MUTANT = "MUTANT";
    final static String NO_MUTANT = "NO-MUTANT";

    private final String examResult;
    private final LocalDate examDate;

    public DnaVerificationAttemptAggregate(boolean examResult){
        this.examResult = examResult ? MUTANT : NO_MUTANT;
        this.examDate = LocalDate.now();
    }

    public String getExamResult(){
        return examResult;
    }

    public LocalDate getExamDate(){
        return examDate;
    }

    /**
     * validate dna attempts and calculate the rate
     *
     * @param dnaVerificationAttempts
     * @return DnaVerification
     */
    public static DnaVerification validateAttempts(List<DnaVerificationAttempt> dnaVerificationAttempts){

        if(dnaVerificationAttempts.isEmpty())
            throw new RuntimeException();
        long numberMutants = dnaVerificationAttempts.stream()
                .filter(sample -> sample.getExamResult().equals(MUTANT))
                .count();

        long numberNoMutants = dnaVerificationAttempts.stream()
                .filter(sample -> sample.getExamResult().equals(NO_MUTANT))
                .count();

        BigDecimal numMutants= new BigDecimal(numberMutants);
        BigDecimal numNoMutants= new BigDecimal(numberNoMutants);
        numNoMutants = numNoMutants.equals(BigDecimal.ZERO) ? BigDecimal.ONE : numNoMutants;
        BigDecimal rate = numMutants.divide(numNoMutants,4, RoundingMode.HALF_EVEN);

        return new DnaVerification(numberMutants, numberNoMutants, rate);
    }
}
