package com.xmen.infrastructure.rest.contracts;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationAverageResponse {

    private DnaAverage dna;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DnaAverage {

        private long count_mutant_dna;
        private long count_human_dna;
        private BigDecimal ratio;
    }
}
