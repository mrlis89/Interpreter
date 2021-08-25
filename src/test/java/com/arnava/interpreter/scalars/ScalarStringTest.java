package com.arnava.interpreter.scalars;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ScalarStringTest {
    @Test
    void toScalar(){
        assertThat(
            new ScalarString("abc")
                .toScalar()
        ).isEqualTo("abc");
    }
}
