package com.xmen.domain.aggregates;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Dna Aggregate
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
public class DnaAggregate {

    static final Integer QUANTITY_DNA_FOR_MUTANT = 3;
    static final Integer QUANTITY_SAMPLE_DNA_ALLOW = 3;
    static final String MOLECULE_ADN_VALID = "[ATCG]+";

    /**
     * validate on the basis of DNA whether it is a mutant or not
     * @param dna
     * @return boolean
     */
    public static boolean isMutant(String[] dna) {

        isValidAdn(Arrays.asList(dna));

        char[][] fullDna = Arrays.stream(dna).map(molecule->molecule.toCharArray())
                .toArray(char[][]::new);

        Collections.reverse(Arrays.asList(dna));
        char[][] fullDnaReverse = Arrays.stream(dna).map(molecule->molecule.toCharArray())
                .toArray(char[][]::new);

        CompletableFuture<Boolean> rows = CompletableFuture.supplyAsync(() -> findMutantRows(fullDna));
        CompletableFuture<Boolean> columns = CompletableFuture.supplyAsync(() -> findMutantColumns(fullDna));
        CompletableFuture<Boolean> obliquesLeft = CompletableFuture.supplyAsync(() -> validateObliques(fullDna));
        CompletableFuture<Boolean> obliquesRight = CompletableFuture.supplyAsync(() -> validateObliques(fullDnaReverse));

        boolean isMutant;
        try {
            Boolean isMutantInRows = rows.get();
            Boolean isMutantInColumns = columns.get();
            Boolean isMutantInObliquesRight = obliquesRight.get();
            Boolean isMutantInObliquesLeft = obliquesLeft.get();

            isMutant = (isMutantInRows || isMutantInColumns
                    || isMutantInObliquesRight || isMutantInObliquesLeft)
                    ? true : false;
        } catch (InterruptedException e) {
           throw new RuntimeException();
        } catch (ExecutionException e) {
            throw new RuntimeException();
        }

        return isMutant;
    }

    /**
     * Validate if DNA is valid
     * @param dna
     * @return an exception if dna is empty or not valid
     */
    private static void isValidAdn(List<String> dna) {

        if (dna.isEmpty()) {
            throw new RuntimeException();
        }
        for (String s : dna) {
            if (!s.matches(MOLECULE_ADN_VALID)) {
                throw new RuntimeException();
            }
        }
    }

    /**
     * find DNA mutant by rows
     * @param dna
     * @return boolean
     */
    private static Boolean findMutantRows(char[][] dna) {

        for (int i = 0; i < dna.length; i++) {
            if(verifyDna(dna[i]))
                return true;
        }
        return false;
    }

    /**
     * find DNA mutant by columns
     * @param dna
     * @return boolean
     */
    private static Boolean findMutantColumns(char[][] dna) {

        boolean isMutant = false;
        int contColumn = dna[0].length;
        for (int i = 0; i < contColumn; i++) {
            char dnaFragment[] = new char[dna.length];
            for (int j = 0; j < dna.length; j++) {
                dnaFragment[j] = dna[j][i];
            }
            isMutant = verifyDna(dnaFragment);
            if(isMutant)
                break;
        }
        return isMutant;
    }

    /**
     * find DNA mutant by Obliques
     * @param dna
     * @return boolean
     */
    private static Boolean validateObliques(char[][] dna) {

        boolean isMutant = false;
        int countAdnFragments = 0;
        int numberFragments = dna.length + dna[0].length - 1;
        int row = 0, column = 0;
        while (countAdnFragments < numberFragments) {
            isMutant = buildFindMutantOblique(row, column, dna);
            if(isMutant)
                break;
            if (row < dna.length - 1) {
                row++;
            } else if (column < dna[0].length - 1) {
                column++;
            }
            countAdnFragments++;
        }
        return isMutant;
    }

    /**
     * build DNA by obliques and send to validate
     * @param dna
     * @return boolean
     */
    private static boolean buildFindMutantOblique(int row, int column, char[][] dna) {

        StringBuilder dnaBuilder = new StringBuilder();
        while (row >= 0 && column < dna[0].length) {
            dnaBuilder.append(dna[row][column]);
            row--;
            column++;
        }
        char dnaFragment[] = new char[dnaBuilder.length()];
        dnaBuilder.getChars(0, dnaBuilder.length(), dnaFragment, 0);

        return verifyDna(dnaFragment);
    }

    /**
     * verify is sample of DNS contains a sequence of DNA mutant
     * @param dna
     * @return boolean
     */
    public static boolean verifyDna(char[] dna){

        int indexDna = 0;
        for(int i=0;i<dna.length;i++) {
            if ((dna.length-indexDna)>QUANTITY_SAMPLE_DNA_ALLOW) {
                indexDna ++;
                int nextIndexDna=1;
                int isMutant = 0;
                for (int j = 0; j < 3; j++) {
                    if(dna[i]==dna[i + nextIndexDna]){
                        isMutant++;
                    }
                    nextIndexDna++;
                }
                if(isMutant==QUANTITY_DNA_FOR_MUTANT)
                    return true;
            }else{
                break;
            }
        }
        return false;
    }
}
