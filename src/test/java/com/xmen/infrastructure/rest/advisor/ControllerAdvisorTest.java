package com.xmen.infrastructure.rest.advisor;

import com.xmen.infrastructure.rest.controllers.ControllerAdvisor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

import static org.junit.Assert.assertThat;
@SpringBootTest
public class ControllerAdvisorTest {

    @Autowired
    private ControllerAdvisor controllerAdvisor;

    /**
     * Given an exception manage for handleTypeMismatch
     * When the handleTypeMismatch method id called
     * Then a ResponseEntity with Forbidden code should be returned
     */
    @Test
    public void verifyDna_withSuccessFlow(){

        final ResponseEntity<?> response = controllerAdvisor
                .handleRuntimeException(new RuntimeException());

        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.FORBIDDEN)));

    }
}
