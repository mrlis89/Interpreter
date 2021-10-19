package com.arnava.interpreter.operators;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MultIntegerTest {
    @Test
    void getArg1() {
        assertThat(
                new MultInteger(5,2)
                        .getArg1()
                        .toScalar()
        ).isEqualTo(5);
    }
    @Test
    void getArg2() {
        assertThat(
                new MultInteger(3,2)
                        .getArg2()
                        .toScalar()
        ).isEqualTo(2);
    }

    @Test
    void toScalar() {
        assertThat(
                new MultInteger(6,2)
                        .toScalar()
        ).isEqualTo(12);
    }

    @Test
    void toScalarWithGrouping() {
        assertThat(
                new MultInteger(
                        new PlusInteger(7,1),
                        new MinusInteger(5,2)
                ).toScalar()
        ).isEqualTo(24);
    }
}