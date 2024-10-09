package com.marginallyclever.convenience.noise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellularNoiseTest {
    private CellularNoise cellularNoise;

    @BeforeEach
    void setUp() {
        cellularNoise = new CellularNoise();
    }

    @Test
    // Result should not be null
    void testNoise1D() {
        double result = cellularNoise.noise(0.5);
        assertNotNull(result);
        assertTrue(result >= 0);

        result = cellularNoise.noise(0.0);
        assertNotNull(result);
        assertTrue(result >= 0);

        result = cellularNoise.noise(-0.5);
        assertNotNull(result);
        assertTrue(result >= 0);
    }

    @Test
    void testNoise2D() {
        double result = cellularNoise.noise(0.5, 0.5);
        assertNotNull(result);
        assertTrue(result >= 0);

        result = cellularNoise.noise(10.0, -3.0);
        assertNotNull(result);
        assertTrue(result >= 0);

        result = cellularNoise.noise(-1.5, -1.5);
        assertNotNull(result);
        assertTrue(result >= 0);
    }

    @Test
    void testNoise3D() {
        double result = cellularNoise.noise(0.5, 0.5, 0.5);
        assertNotNull(result);
        assertTrue(result >= 0);

        result = cellularNoise.noise(-9.0, 0.0, 1.0);
        assertNotNull(result);
        assertTrue(result >= 0);

        result = cellularNoise.noise(-3.5, -0.5, -6.0);
        assertNotNull(result);
        assertTrue(result >= 0);
    }

    @Test
    // Minimum distance should be non-negative
    void testMinimumDistance() {
        double result = cellularNoise.noise(1.0, 2.0, 3.0);
        assertTrue(result >= 0);
    }

    @Test
    // Process voxel should yield non-negative noise value
    void testProcessVoxel() {
        double result = cellularNoise.noise(-3.0, -2.0, 1.0);
        assertTrue(result >= 0);
    }

}
