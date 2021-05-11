package com.xmen.domain.aggregates;

import com.xmen.domain.entities.VerificationAttempt;
import com.xmen.domain.vo.Verification;

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
public class VerificationAggregate {
    final static String MUTANT = "MUTANT";
    final static String NO_MUTANT = "NO-MUTANT";

    private final String examResult;
    private final LocalDate examDate;

    public VerificationAggregate(boolean examResult){
        this.examResult = examResult ? MUTANT : NO_MUTANT;
        this.examDate = LocalDate.now();
    }

    public String getExamResult(){
        return examResult;
    }

    public LocalDate getExamDate(){
        return examDate;
    }

    public static Verification  validateAttempts(List<VerificationAttempt> verificationAttempts){


        long numberMutants = verificationAttempts.stream()
                .filter(sample -> sample.getExamResult().equals(MUTANT))
                .count();

        long numberNoMutants = verificationAttempts.stream()
                .filter(sample -> sample.getExamResult().equals(NO_MUTANT))
                .count();

        BigDecimal numMutants= new BigDecimal(numberMutants);
        BigDecimal numNoMutants= new BigDecimal(numberNoMutants);
        numNoMutants = numNoMutants.equals(BigDecimal.ZERO) ? BigDecimal.ONE : numNoMutants;
        BigDecimal rate = numMutants.divide(numNoMutants,4, RoundingMode.HALF_EVEN);

        return new Verification(numberMutants, numberNoMutants, rate);
    }
}
