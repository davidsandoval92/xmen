package com.xmen.domain.entities;

import java.time.LocalDate;

/**
 * verification attempt entity
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */

public class VerificationAttempt {

    private final long id;
    private final String examResult;
    private final LocalDate examDate;

    public VerificationAttempt(long id, String examResult, LocalDate examDate){
        this.id = id;
        this.examResult = examResult;
        this.examDate = examDate;
    }

    public long getId(){
        return id;
    }

    public String getExamResult(){
        return examResult;
    }

    public LocalDate getExamDate(){
        return examDate;
    }
}
