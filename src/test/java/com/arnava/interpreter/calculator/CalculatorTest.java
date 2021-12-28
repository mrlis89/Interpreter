package com.arnava.interpreter.calculator;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculate() {
        Calculator calc = new Calculator();
        assertThat(calc.calculate("(3+ 1) * 7")).isEqualTo(28);
    }
}