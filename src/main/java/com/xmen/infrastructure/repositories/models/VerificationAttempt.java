package com.xmen.infrastructure.repositories.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="verification_attempts")
public class VerificationAttempt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String examResult;
    private LocalDate examDate;

    public VerificationAttempt(String examResult, LocalDate examDate) {
        this.examResult = examResult;
        this.examDate = examDate;
    }
}
