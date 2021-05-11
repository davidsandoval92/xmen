package com.xmen.domain.aggregates;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Dna Aggregate
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */

public class DnaAggregate {

    static final Integer QUANTITY_DNA_FOR_MUTANT = 4;
    static final Integer QUANTITY_SAMPLE_DNA_ALLOW = 4;
    static final String MOLECULE_ADN_VALID = "ATCG";

    public static boolean isMutant(String[] dna) {

        isValidAdn(dna);

        char[][] fullDna = new char[dna.length][];
        for (int i = 0; i < dna.length; i++) {
            fullDna[i] = dna[i].toCharArray();
        }

        //System.out.println(Arrays.deepToString(fullDna).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        CompletableFuture<Boolean> rows = CompletableFuture.supplyAsync(() -> findMutantRows(fullDna));
        CompletableFuture<Boolean> columns = CompletableFuture.supplyAsync(() -> findMutantColumns(fullDna));
        CompletableFuture<Boolean> obliquesRight = CompletableFuture.supplyAsync(() -> validateObliquesRight(fullDna));
        CompletableFuture<Boolean> obliquesLeft = CompletableFuture.supplyAsync(() -> validateObliquesLeft(fullDna));

        Boolean isMutantInRows = false;
        Boolean isMutantInColumns = false;
        Boolean isMutantInObliquesRight = false;
        Boolean isMutantInObliquesLeft = false;

        try {
            isMutantInRows = rows.get();
            isMutantInColumns = columns.get();
            isMutantInObliquesRight = obliquesRight.get();
            isMutantInObliquesLeft = obliquesLeft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        boolean isMutant = (isMutantInRows || isMutantInColumns
                || isMutantInObliquesRight || isMutantInObliquesLeft)
                ? true : false;

        return isMutant;
    }

    private static void isValidAdn(String[] dna) {
        if (Objects.isNull(dna)) {
            throw new RuntimeException();
        }

        String joinDna = Arrays.stream(dna)
                .collect(Collectors.joining());

        String[] dnaMolecule = joinDna.split("");

        boolean isNotValid = Arrays.stream(dnaMolecule)
                .anyMatch(elem -> !MOLECULE_ADN_VALID.contains(elem));

        if (isNotValid) {
            throw new RuntimeException();
        }
    }

    private static Boolean findMutantRows(char[][] dna) {
        boolean isMutant = false;
        for (int i = 0; i < dna.length; i++) {
            isMutant = verifyDna(dna[i]);
            if(isMutant)
                break;
        }
        return isMutant;
    }

    private static Boolean findMutantColumns(char[][] dna) {
        boolean isMutant = false;
        int contColumn = dna[0].length;
        for (int i = 0; i < contColumn; i++) { //loops through columns
            char dnaFragment[] = new char[dna.length];
            for (int j = 0; j < dna.length; j++) {//loops through rows
                dnaFragment[j] = dna[j][i];
            }
            isMutant = verifyDna(dnaFragment);
            if(isMutant)
                break;
        }
        return isMutant;
    }

    private static Boolean validateObliquesRight(char[][] dna) {

        boolean isMutant = false;
        int adnFragments = 0;
        //cantidad de diagonales
        int count = dna.length + dna[0].length - 1;
        int row = 0, column = 0;
        while (adnFragments < count) {
            isMutant = findMutantObliqueRight(row, column, dna);
            if(isMutant)
                break;
            if (row < dna.length - 1) {
                //Increment row index until we reach the max number of rows
                row++;
            } else if (column < dna[0].length - 1) {
                //If maximum index of row, can increment columns index
                //Increment column index until we reach the max number of columns
                column++;
            }
            adnFragments++;
        }
        return isMutant;
    }

    private static boolean findMutantObliqueRight(int row, int column, char[][] dna) {
        StringBuilder dnaBuilder = new StringBuilder();
        //recorre la diagonal de derecha a izquierda y almacena el valor en dnaBuilder
        while (row >= 0 && column < dna[0].length) {
            dnaBuilder.append(dna[row][column]);
            row--;
            column++;
        }

        char dnaFragment[] = new char[dnaBuilder.length()];
        dnaBuilder.getChars(0, dnaBuilder.length(), dnaFragment, 0);

        return verifyDna(dnaFragment);
    }

    private static Boolean validateObliquesLeft(char[][] dna) {

        boolean isMutant = false;
        int adnFragments = 0;
        //cantidad de diagonales
        int count = dna.length + dna[0].length - 1;
        int row = 0, column = dna.length - 1;
        while (adnFragments < count) {
            isMutant = findMutantObliqueLeft(row, column, dna);
            if(isMutant)
                break;
            if (column > 0) {
                //Increment column index until we reach the max number of column
                column--;
            } else if (row <= dna.length - 1) {
                //If maximum index of columns, can increment row index
                //Increment row index until we reach the max number of rows
                row++;
            }
            adnFragments++;
        }
        return isMutant;
    }

    private static Boolean findMutantObliqueLeft(int row, int column, char[][] dna) {

        StringBuilder dnaBuilder = new StringBuilder();
        //recorre la diagonal de izquierda a derecha y almacena el valor en dnaBuilder
        while (row < dna[0].length && column < dna[0].length) {
            dnaBuilder.append(dna[row][column]);
            row++;
            column++;
        }

        char dnaFragment[] = new char[dnaBuilder.length()];
        dnaBuilder.getChars(0, dnaBuilder.length(), dnaFragment, 0);

        return verifyDna(dnaFragment);
    }

    private static boolean verifyDna(char[] dna) {

        boolean isMutant = false;
        if (dna.length >= QUANTITY_SAMPLE_DNA_ALLOW) {
            // recorre cada posicion de la muestra de ADN
            for (int startPosDna = 0; startPosDna < dna.length; startPosDna++) {
                //valida que la muestra a verificar tenga mas de 4 posiciones para seguir recorriendola
                //y valida si ya fue encontrada un fragmento postivo para mutante
                boolean isLengthAllow = dna.length - startPosDna >= 4;
                if (isLengthAllow && !isMutant) {
                    //lleva el valor de cantidad de moleculas de adn encontradas.
                    int sequenceQuantity = 0;
                    //lleva el valor si la molecula al comparar no es la misma en la siguiente posicion (A->B)
                    //para pasar a la sigueinte posicion de molecula
                    boolean isDifferent = false;

                    //recorre el numero todas de veces que se debe repetir la secuencia de moleculas (4)
                    for (int sequenceDnaLength = 1; sequenceDnaLength <= 3; sequenceDnaLength++) {
                        if (!isDifferent) {
                            //lleva el valor de si la secuencia se cumplio
                            boolean sequencesAreEqual = false;
                            //verifica si la molecula con la que se inicio a validar es la misma a la posicion
                            //siguiente. startPos + sequenceLength
                            for (int i = 0; i < sequenceDnaLength; i++) {
                                //valida si se completo el valor de la secuancia para ser mutante
                                //para no buscar mas
                                if (sequenceQuantity == QUANTITY_DNA_FOR_MUTANT) {
                                    sequencesAreEqual = true;
                                    break;
                                }
                                //valida la si las dos moleculas de adn son iguales en sus respectivas posiciones
                                //si no es igual, no busca mas y pasa a la siguiente molecula
                                if (!(dna[startPosDna + i] == dna[startPosDna + sequenceDnaLength + i])) {
                                    sequencesAreEqual = false;
                                    isDifferent = true;
                                    break;
                                } else {
                                    //si la molecula es igual incremente su valor para despues ser comparada
                                    //si cumple o no con la cantidad asignada de adn para ser mutante
                                    sequenceQuantity++;
                                }
                            }
                            if (sequencesAreEqual) {
                                isMutant = true;
                                break;
                            }
                        }else{
                            break;
                        }
                    }
                }else{
                    break;
                }
            }
        }
        return isMutant;
    }
}
