package com.marginallyclever.convenience.noise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoiseFactoryTest {

    @Test
    void testGetNames() {
        String[] names = NoiseFactory.getNames();
        assertNotNull(names, "Names array should not be null");
        assertEquals(3, names.length, "Names array should contain three elements");
        assertEquals("Perlin", names[0]);
        assertEquals("Simplex", names[1]);
        assertEquals("Cellular", names[2]);
    }

    @Test
    void testGetNoiseValidIndices() {
        // Test valid indices for getting noise instances
        Noise perlinNoise = NoiseFactory.getNoise(0);
        assertNotNull(perlinNoise, "Perlin noise should not be null");
        assertTrue(perlinNoise instanceof PerlinNoise, "Should return an instance of PerlinNoise");

        Noise simplexNoise = NoiseFactory.getNoise(1);
        assertNotNull(simplexNoise, "Simplex noise should not be null");
        assertTrue(simplexNoise instanceof SimplexNoise, "Should return an instance of SimplexNoise");

        Noise cellularNoise = NoiseFactory.getNoise(2);
        assertNotNull(cellularNoise, "Cellular noise should not be null");
        assertTrue(cellularNoise instanceof CellularNoise, "Should return an instance of CellularNoise");
    }

    @Test
    void testGetNoiseInvalidIndex() {
        // Test invalid index for getting noise instance
        Noise noise = NoiseFactory.getNoise(-1);
        assertNull(noise);

        noise = NoiseFactory.getNoise(3);
        assertNull(noise);
    }

   
}
