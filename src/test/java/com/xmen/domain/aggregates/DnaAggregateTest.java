package com.xmen.domain.aggregates;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@SpringBootTest
public class DnaAggregateTest {

    /**
     * Given an DNA invalid
     * When isMutant method id called
     * Then RuntimeException should be returned
     */
    @Test()
    public void dnaInvalid_withFailFlow(){

        String[] dna = {"GAXAGG","ATCCAA","GAAGGA","AAATGG","CATCTA","GCTACG"};

        Assertions.assertThrows(RuntimeException.class, () -> {
            DnaAggregate.isMutant(dna);
        });

    }

    /**
     * Given an DNA empty
     * When isMutant method id called
     * Then RuntimeException should be returned
     */
    @Test()
    public void dnaEmpty_withFailFlow(){

        String[] dna = {};

        Assertions.assertThrows(RuntimeException.class, () -> {
            DnaAggregate.isMutant(dna);
        });

    }

    /**
     * Given an DNA without DNA mutant molecules in rows
     * When isMutant method id called
     * Then boolean false should be returned
     */
    @Test
    public void isMutant_withFailFlow(){

        String[] dna = {"GAAAGG","ATCCAA","GAAGGA","AAATGG","CATCTA","GCTACG"};

        final boolean isMutant = DnaAggregate.isMutant(dna);

        assertThat(isMutant, is(equalTo(false)));

    }

    /**
     * Given an DNA with DNA mutant molecules in rows
     * When isMutant method id called
     * Then boolean true should be returned
     */
    @Test
    public void isMutantInRowDna_withSuccessFlow(){

        String[] dna = {"AAAAGA","ATCCAA","GAAGGT","AAATGG","CATCTA","GCTACG"};

        final boolean isMutant = DnaAggregate.isMutant(dna);

        assertThat(isMutant, is(equalTo(true)));

    }

    /**
     * Given an DNA with DNA mutant molecules in column
     * When isMutant method id called
     * Then boolean true should be returned
     */
    @Test
    public void isMutantInColumnDna_withSuccessFlow(){

        String[] dna = {"GAAAGA","ATCCAA","GAAGGT","AAATGG","CATCTA","GATACG"};

        final boolean isMutant = DnaAggregate.isMutant(dna);

        assertThat(isMutant, is(equalTo(true)));

    }

    /**
     * Given an DNA with DNA mutant molecules in diagonal right
     * When isMutant method id called
     * Then boolean true should be returned
     */
    @Test
    public void isMutantInDiagonalRightDna_withSuccessFlow(){

        String[] dna = {"GTGCGA","CAGTGC","ATATGA","AAGAAG","CCAAGG","TCAGTG"};

        final boolean isMutant = DnaAggregate.isMutant(dna);

        assertThat(isMutant, is(equalTo(true)));

    }

    /**
     * Given an DNA with DNA mutant molecules in diagonal left
     * When isMutant method id called
     * Then boolean true should be returned
     */
    @Test
    public void isMutantInDiagonalLeftDna_withSuccessFlow(){

        String[] dna = {"GATAGG","ATCTAA","GAAGTA","AAATGT","CATCTA","GCTACG"};

        final boolean isMutant = DnaAggregate.isMutant(dna);

        assertThat(isMutant, is(equalTo(true)));

    }
}
