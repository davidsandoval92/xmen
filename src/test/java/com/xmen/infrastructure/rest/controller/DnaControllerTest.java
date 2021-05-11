package com.xmen.infrastructure.rest.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmen.application.usecases.MutantUseCase;
import com.xmen.domain.vo.DnaVerification;
import com.xmen.infrastructure.rest.contracts.SpecimenRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;


@SpringBootTest
@AutoConfigureMockMvc
public class DnaControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private MutantUseCase useCase;

    /**
     * Given a verify a DNA mutant
     * When the verifyDna is called and use case response true
     * Then an Ok response is returned
     */
    @Test
    public void verifyDna_withSuccessFlow() throws Exception {

        String[] dna = {"AAAAGA","ATCCAA","GAAGGT","AAATGG","CATCTA","GCTACG"};

        SpecimenRequest request = SpecimenRequest.builder()
                .dna(dna).build();

        when(useCase.isMutant(Mockito.any())).thenReturn(true);

        mvc.perform(post("/mutant/")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(useCase).isMutant(Mockito.any());
    }

    /**
     * Given a verify a DNA human
     * When the verifyDna is called and use case response false
     * Then an Forbidden response is returned
     */
    @Test
    public void verifyDna_withErrorFlow() throws Exception {

        String[] dna = {"AAAAGA","ATCCAA","GAAGGT","AAATGG","CATCTA","GCTACG"};

        SpecimenRequest request = SpecimenRequest.builder()
                .dna(dna).build();

        when(useCase.isMutant(Mockito.any())).thenReturn(false);

        mvc.perform(post("/mutant/")
                .content(new ObjectMapper().writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

        verify(useCase).isMutant(Mockito.any());
    }

    /**
     * Given a verify a validation averages
     * When the validationAverages is called and use case response ok
     * Then an Ok response is returned with ValidationAverageResponse
     */
    @Test
    public void validationAverages_withSuccessFlow() throws Exception {

        DnaVerification dnaVerification = new DnaVerification(40,140,new BigDecimal(0.4));

        when(useCase.validateAttempts()).thenReturn(dnaVerification);

        mvc.perform(get("/stats")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.dna.count_mutant_dna", is(40)))
                .andExpect(status().isOk());

        verify(useCase).validateAttempts();
    }
}
