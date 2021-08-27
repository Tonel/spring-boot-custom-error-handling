package com.customerrorhandling.demo.controllers;

import exceptions.CustomDataNotFoundException;
import exceptions.CustomErrorException;
import exceptions.CustomParameterConstraintException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {
    @GetMapping("test-custom-data-not-found-exception")
    public ResponseEntity<Void> test1() {
        try {
            // simulating a NullPointerException error
            throw new NullPointerException("Data not found");
        } catch (NullPointerException e) {
            throw new CustomDataNotFoundException(e.getMessage());
        }
    }

    @GetMapping("test-custom-parameter-constraint-exception")
    public ResponseEntity<Void> test2(
            @RequestParam("value") int value
    ) {
        if (value < 0 || value > 10) {
            throw new CustomParameterConstraintException("value must be >= 0 and <= 10");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("test-custom-error-exception")
    public ResponseEntity<Void> test3() {
        // simulating a CustomDataNotFoundException error
        throw new CustomErrorException(
                HttpStatus.BAD_REQUEST,
                "Parameters not passed"
        );
    }

    @GetMapping("test-generic-exception")
    public ResponseEntity<Void> test4() {
        // simulating a generic error
        throw new RuntimeException(
                "Generic Exception"
        );
    }
}
