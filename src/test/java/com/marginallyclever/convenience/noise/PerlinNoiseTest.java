package com.marginallyclever.convenience.noise;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PerlinNoiseTest {
    private PerlinNoise perlinNoise;

    @BeforeEach
    void setUp() {
        perlinNoise = new PerlinNoise();
    }

    @Test
    void testNoise1DWithinRange() {
        double result = perlinNoise.noise(0.5);
        assertTrue(result >= -1 && result <= 1);
    }

    @Test
    void testNoise2DWithinRange() {
        double result = perlinNoise.noise(0.5, 1.5);
        assertTrue(result >= -1 && result <= 1);
    }

    @Test
    void testNoise3DWithinRange() {
        double result = perlinNoise.noise(1.5, 2.5, 3.5);
        assertTrue(result >= -1 && result <= 1);
    }

    @Test
    void testNoiseConsistency() {
        double result1 = perlinNoise.noise(0.5, 0.5, 0.5);
        double result2 = perlinNoise.noise(0.5, 0.5, 0.5);
        assertEquals(result1, result2);
    }

}
