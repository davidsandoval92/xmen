package com.xmen.infrastructure.rest.controllers;

import com.xmen.application.usecases.MutantUseCase;
import com.xmen.domain.vo.Verification;
import com.xmen.infrastructure.rest.contracts.SpecimenRequest;
import com.xmen.infrastructure.rest.contracts.ValidationAverageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class MutantController {

    private final MutantUseCase useCase;

    public MutantController(MutantUseCase useCase){
        this.useCase = useCase;
    }

    @PostMapping("/mutant/")
    public ResponseEntity<Void> verifyDna(@Valid @RequestBody final SpecimenRequest request){

        return useCase.isMutant(request.getDna()) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.FORBIDDEN);

    }

    @GetMapping("/stats")
    public ResponseEntity<ValidationAverageResponse> validationAverages(){

        Verification verification = useCase.validateAttempts();
        return new ResponseEntity<>(new ValidationAverageResponse(
                new ValidationAverageResponse.DnaAverage(
                        verification.getCountMutantDna(),
                        verification.getCountHumanDna(),
                        verification.getRatio())),HttpStatus.OK);
    }
}
