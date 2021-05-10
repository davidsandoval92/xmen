package com.xmen.domain.aggregates;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class VerificationAggregate {
    final static String MUTANT = "MUTANT";
    final static String NO_MUTANT = "NO-MUTANT";
    private final String examResult;
    private final LocalDate examDate;

    public VerificationAggregate(boolean examResult){
        this.examResult = examResult ? MUTANT : NO_MUTANT;
        this.examDate = LocalDate.now();
    }
}
