package com.arnava.interpreter.operators;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlusIntegerTest {
    @Test
    void getArg1() {
        assertThat(
            new PlusInteger(1,2)
                    .getArg1()
                    .toScalar()
        ).isEqualTo(1);
    }
    @Test
    void getArg2() {
        assertThat(
            new PlusInteger(3,2)
                    .getArg2()
                    .toScalar()
        ).isEqualTo(2);
    }

    @Test
    void toScalar() {
        assertThat(
            new PlusInteger(3,2)
                    .toScalar()
        ).isEqualTo(5);
    }

    @Test
    void toScalarWithGrouping() {
        assertThat(
            new PlusInteger(
                new PlusInteger(1,1),
                new PlusInteger(1,1)
            ).toScalar()
        ).isEqualTo(4);
    }
}
