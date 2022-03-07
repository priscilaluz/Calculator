package com.wit.calculator.service;

import java.math.BigDecimal;

public interface Operation {
    BigDecimal calculation(BigDecimal a, BigDecimal b);
}
