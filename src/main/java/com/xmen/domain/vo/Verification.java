package com.xmen.domain.vo;

import java.math.BigDecimal;

/**
 * Verification information
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public class Verification {

    private final long countMutantDna;
    private final long countHumanDna;
    private final BigDecimal ratio;

    public Verification(long countMutantDna, long countHumanDna, BigDecimal ratio){
        this.countMutantDna = countMutantDna;
        this.countHumanDna = countHumanDna;
        this.ratio = ratio;
    }

    public long getCountMutantDna(){
        return countMutantDna;
    }

    public long getCountHumanDna(){
        return countHumanDna;
    }

    public BigDecimal getRatio(){
        return ratio;
    }

}
