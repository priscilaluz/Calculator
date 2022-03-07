package com.wit.calculator.service.impl;

import com.wit.calculator.service.Operation;

import java.math.BigDecimal;

public class Multiplication implements Operation {

    @Override
    public BigDecimal calculation(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }
}
