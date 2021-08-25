package com.arnava.interpreter.scalars;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScalarIntegerTest {
    @Test
    void toScalar(){
        assertThat(
                new ScalarInteger(5)
                        .toScalar()
        )
        .isEqualTo(5);
    }
}
