package com.arnava.interpreter.operators;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ModIntegerTest {
    @Test
    void toScalar(){
        assertThat(
            new ModInteger(-3)
                .toScalar()
        ).isEqualTo(3);

        assertThat(
            new ModInteger(3)
                .toScalar()
        ).isEqualTo(3);
    }
}
