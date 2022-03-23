package com.arnava.interpreter.calculator;

import com.arnava.interpreter.exceptions.BracketsCountException;
import com.arnava.interpreter.exceptions.OperatorOrderException;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculate() throws BracketsCountException, OperatorOrderException {
        Calculator calc = new Calculator();
        assertThat(calc.calculate("(11 - 7-3) * 21")).isEqualTo(21);
    }
}