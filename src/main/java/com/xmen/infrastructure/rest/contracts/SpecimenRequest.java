package com.xmen.infrastructure.rest.contracts;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Used to receive data with DNA request to validate if mutant
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecimenRequest {

    @NotNull(message = "DNA cannot be null")
    @Valid
    private String[] dna;
}
