package com.xmen.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class VerificationAttempt {

    private final long id;
    private final String examResult;
    private final LocalDate examDate;
}
