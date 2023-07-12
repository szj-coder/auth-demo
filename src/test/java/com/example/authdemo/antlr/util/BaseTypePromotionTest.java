package com.example.authdemo.antlr.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.example.authdemo.antlr.util.BaseTypePromotion.*;

class BaseTypePromotionTest {

    @Test
    void compareToTest() {
        Assertions.assertEquals(-1, compareTo(1, 2));
        Assertions.assertEquals(1, compareTo(3, 2));
        Assertions.assertEquals(0, compareTo(2, 2));
        Assertions.assertEquals(-1, compareTo(1, Short.valueOf("2")));
        Assertions.assertEquals(0, compareTo(1, 1.0));
        Assertions.assertEquals(-1, compareTo(1, "2"));
        Assertions.assertEquals(-1, compareTo("1", "2"));

        Assertions.assertThrows(RuntimeException.class, () -> {
            plus(1, new Object());
        });
        Assertions.assertThrows(RuntimeException.class, () -> {
            plus(1, null);
        });
    }

    @Test
    void plusTest() {
        Assertions.assertEquals(3, plus(1, 2));
        Assertions.assertEquals(3, plus(1, Short.valueOf("2")));
        Assertions.assertEquals(2.0, plus(1, 1.0));
        Assertions.assertEquals("12", plus(1, "2"));
        Assertions.assertEquals("12", plus("1", "2"));

        Assertions.assertThrows(RuntimeException.class, () -> {
            plus(1, new Object());
        });
        Assertions.assertThrows(RuntimeException.class, () -> {
            plus(1, null);
        });
    }

    @Test
    void minusTest() {
        Assertions.assertEquals(-1, minus(1, 2));
        Assertions.assertEquals(-1, minus(1, Short.valueOf("2")));
        Assertions.assertEquals(0.0, minus(1, 1.0));
        Assertions.assertThrows(RuntimeException.class, () -> {
            minus(1, "2");
        });
    }
}