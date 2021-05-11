package com.xmen.infrastructure.rest.contracts;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Used to retrieve data with validations verified
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
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
