package com.xmen.infrastructure.rest.controllers;

import com.xmen.application.usecases.MutantUseCase;
import com.xmen.domain.vo.DnaVerification;
import com.xmen.infrastructure.rest.contracts.SpecimenRequest;
import com.xmen.infrastructure.rest.contracts.ValidationAverageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * This controller allows manage DNA operations
 *
 * @author <a href="davidsandoval9217@gmail.com"> David Sandoval</a>
 * @since 1.0
 * @version 1.0
 */
@Slf4j
@RestController
public class DnaController {

    private final MutantUseCase useCase;

    public DnaController(MutantUseCase useCase){
        this.useCase = useCase;
    }

    /**
     * Allow verify is a DNA is mutant or not
     *
     * @param request
     * @return ResponseEntity<Void>
     */
    @PostMapping("/mutant/")
    public ResponseEntity<Void> verifyDna(@Valid @RequestBody final SpecimenRequest request) throws Exception {

        return useCase.isMutant(request.getDna()) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

    /**
     * Get stats of validation mutants
     * @return ResponseEntity<ValidationAverageResponse>
     */
    @GetMapping("/stats")
    public ResponseEntity<ValidationAverageResponse> validationAverages() throws Exception {

        DnaVerification dnaVerification = useCase.validateAttempts();
        return new ResponseEntity<>(new ValidationAverageResponse(
                new ValidationAverageResponse.DnaAverage(
                        dnaVerification.getCountMutantDna(),
                        dnaVerification.getCountHumanDna(),
                        dnaVerification.getRatio())),HttpStatus.OK);
    }

    /**
     * Allows to configure safe settings for @RequestBody annotated arguments in controller methods
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setDisallowedFields();
    }
}
