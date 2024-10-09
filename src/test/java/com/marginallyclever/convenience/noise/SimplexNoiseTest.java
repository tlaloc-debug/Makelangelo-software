package com.marginallyclever.convenience.noise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimplexNoiseTest {

    private final SimplexNoise simplexNoise = new SimplexNoise();

    @Test
    public void testNoise1D() {
        double result = simplexNoise.noise(0.3);
        assertTrue(result >= -1.0 && result <= 1.0, "1D noise value should be in the range [-1, 1]");
    }

    @Test
    public void testNoise2D() {
        double result = simplexNoise.noise(0.5, -1.5);
        assertTrue(result >= -1.0 && result <= 1.0);
    }

    @Test
    public void testNoise3D() {
        double result = simplexNoise.noise(1.5, -2.5, 3.5);
        assertTrue(result >= -1.0 && result <= 1.0);
    }

    @Test
    public void testNoise4D() {
        double result = simplexNoise.noise(-0.5, -0.5, -0.5, -0.5);
        assertTrue(result >= -1.0 && result <= 1.0);
    }

    @Test
    // Noise output should be consistent for the same input
    public void testConsistentOutputForSameInput() {
        double result1 = simplexNoise.noise(1.0);
        double result2 = simplexNoise.noise(1.0);
        assertEquals(result1, result2, "");
    }

    @Test
    // Noise output should not be the same for different input
    public void testNoiseWithDifferentInputs() {
        double result1 = simplexNoise.noise(1.0);
        double result2 = simplexNoise.noise(1.1);
        assertNotEquals(result1, result2);
    }

    @Test
    public void testNoiseForNegativeInputs() {
        double result = simplexNoise.noise(-2.3);
        assertTrue(result >= -1.0 && result <= 1.0);
    }

    @Test
    public void testPerformance() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            simplexNoise.noise(0.5, -1.5);
        }
        long duration = System.currentTimeMillis() - startTime;
        assertTrue(duration < 1000, "Performance test failed: noise generation took too long");
    }

    @Test
    void testNoise3DElseCase() {
        // Testing the 3D noise method with specific inputs that lead to the else case
        double result = simplexNoise.noise(0.0, 1.0, 1.0);
        assertTrue(result >= -1.0 && result <= 1.0);
        
        result = simplexNoise.noise(1.0, 1.0, 0.0);
        assertTrue(result >= -1.0 && result <= 1.0);
        
        result = simplexNoise.noise(1.0, 0.0, 1.0);
        assertTrue(result >= -1.0 && result <= 1.0);
    }

    @Test
    void testNoise4DElseCase() {
        // Testing the 4D noise method with specific inputs that lead to the else case
        double result = simplexNoise.noise(-0.5, 1.5, -3.5, 2.5);
        assertTrue(result >= -1.0 && result <= 1.0);
        
        result = simplexNoise.noise(0.5, -1.5, 3.5, -2.5);
        assertTrue(result >= -1.0 && result <= 1.0);
        
    }

    @Test
    public void testNoiseAtOrigin() {
        double result = simplexNoise.noise(0, 0, 0);
        double expected = 0; 
        assertEquals(expected, result, 1e-6, "Noise at origin should be " + expected);
    }

    @Test
    // Noise should be consistent for the same input coordinates
    public void testNoiseSymmetry() {
        double result1 = simplexNoise.noise(1.1, 2.2, 3.3);
        double result2 = simplexNoise.noise(1.1, 2.2, 3.3);
        assertEquals(result1, result2, 1e-6);
    }

    @Test
    // Noise values should differ for different coordinates
    public void testNoiseDifferentPoints() {
        double result1 = simplexNoise.noise(1.1, 2.2, 3.3);
        double result2 = simplexNoise.noise(4.4, 5.5, 6.6);
        assertEquals(false, result1 == result2);
    }

}
