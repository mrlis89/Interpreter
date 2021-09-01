package com.arnava.interpreter.operators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlusIntegerTest {
    @Test
    void getArg1() {
        assertEquals(
            1,
            new PlusInteger(1,2).getArg1().toScalar()
        );
    }
    @Test
    void getArg2() {
        assertEquals(
            2,
            new PlusInteger(3,2).getArg2().toScalar()
        );
    }

    @Test
    void toScalar() {
        assertEquals(
            5,
            new PlusInteger(3,2).toScalar()
        );
    }

    @Test
    void toScalarWithGrouping() {
        assertEquals(
            4,
            new PlusInteger(
                new PlusInteger(1,1),
                new PlusInteger(1,1)
            ).toScalar()
        );
    }
}
