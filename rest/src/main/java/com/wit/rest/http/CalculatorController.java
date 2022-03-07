package com.wit.rest.http;

import com.wit.rest.http.response.DefaultResponse;
import com.wit.rest.service.CalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CalculatorController {

    private final CalculatorService calculatorService;

    @GetMapping("/sum")
    public ResponseEntity<DefaultResponse> sum(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) throws Exception {
        String response = calculatorService.calculation(a, b, "Sum");
        return new ResponseEntity<>(new DefaultResponse(response), HttpStatus.OK);
    }

    @GetMapping("/subtraction")
    public ResponseEntity<DefaultResponse> subtraction(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) throws Exception {
        String response = calculatorService.calculation(a, b, "Subtraction");
        return new ResponseEntity<>(new DefaultResponse(response), HttpStatus.OK);
    }

    @GetMapping("/multiplication")
    public ResponseEntity<DefaultResponse> multiplication(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) throws Exception {
        String response = calculatorService.calculation(a, b, "Multiplication");
        return new ResponseEntity<>(new DefaultResponse(response), HttpStatus.OK);
    }

    @GetMapping("/division")
    public ResponseEntity<DefaultResponse> division(@RequestParam("a") BigDecimal a, @RequestParam("b") BigDecimal b) throws Exception {
        String response = calculatorService.calculation(a, b, "Division");
        return new ResponseEntity<>(new DefaultResponse(response), HttpStatus.OK);
    }

}
