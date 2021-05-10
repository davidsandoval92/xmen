package com.xmen.infrastructure.rest.contracts;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecimenRequest {

    @NotNull(message = "DNA cannot be null")
    @Valid
    private String[] dna;
}
