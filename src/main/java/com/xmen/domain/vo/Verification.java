package com.xmen.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Verification {

    private final long countMutantDna;
    private final long countHumanDna;
    private final BigDecimal ratio;

}
