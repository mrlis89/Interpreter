package com.arnava.interpreter.operators;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DivIntegerTest {
    @Test
    void getArg1() {
        assertThat(
                new DivInteger(5,2)
                        .getArg1()
                        .toScalar()
        ).isEqualTo(5);
    }
    @Test
    void getArg2() {
        assertThat(
                new DivInteger(3,2)
                        .getArg2()
                        .toScalar()
        ).isEqualTo(2);
    }

    @Test
    void toScalar() {
        assertThat(
                new DivInteger(6,2)
                        .toScalar()
        ).isEqualTo(3);
    }

    @Test
    void toScalarWithGrouping() {
        assertThat(
                new DivInteger(
                        new PlusInteger(7,1),
                        new MultInteger(1,2)
                ).toScalar()
        ).isEqualTo(4);
    }
}