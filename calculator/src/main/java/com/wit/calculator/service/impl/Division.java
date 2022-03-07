package com.wit.calculator.service.impl;

import com.wit.calculator.service.Operation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Division implements Operation {

    @Override
    public BigDecimal calculation(BigDecimal a, BigDecimal b) {
        return a.divide(b, 2, RoundingMode.HALF_UP);
    }
}
