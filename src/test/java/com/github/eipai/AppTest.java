package com.github.eipai;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private static String get() {
        return "TEST";
    }

    @Test
    @DisplayName("Test AppTest.get()")
    void testGet() {
        assertEquals("TEST", AppTest.get());
    }

}
