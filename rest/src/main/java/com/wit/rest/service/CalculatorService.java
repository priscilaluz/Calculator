package com.wit.rest.service;

import com.wit.rest.client.RabbitMqClient;
import com.wit.rest.exception.BadRequestException;
import com.wit.rest.exception.InternalServerErroException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Slf4j
@Service
@AllArgsConstructor
public class CalculatorService {

    private final RabbitMqClient rabbitMqClient;

    public String calculation(BigDecimal a, BigDecimal b, String operation) {
        String response;
        try {
            response = rabbitMqClient.send(String.format("%s,%s,%s", a, b, operation));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new InternalServerErroException(ex.getMessage());
        }
        if (response.startsWith("ERROR")) {
            log.error(response);
            throw new BadRequestException(response);
        }
        return response;
    }

}
