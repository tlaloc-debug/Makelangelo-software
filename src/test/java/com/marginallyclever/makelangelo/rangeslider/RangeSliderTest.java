package com.marginallyclever.makelangelo.rangeslider;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JSlider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RangeSliderTest {
    private RangeSlider rangeSlider;

    @BeforeEach
    public void setUp() {
        rangeSlider = new RangeSlider(0, 100);
    }

    @Test
    public void testDefaultConstructor() {
        RangeSlider defaultSlider = new RangeSlider();
        assertEquals(0, defaultSlider.getMinimum());
        assertEquals(100, defaultSlider.getMaximum());
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals(0, rangeSlider.getMinimum());
        assertEquals(100, rangeSlider.getMaximum());
    }

    @Test
    public void testInitSlider() {
        assertEquals(JSlider.HORIZONTAL, rangeSlider.getOrientation());
    }

    // This tests if the method executes without error.
    @Test
    public void testUpdateUI() {
        assertDoesNotThrow(() -> rangeSlider.updateUI());
    }

    @Test
    public void testGetValue() {
        rangeSlider.setValue(30);
        assertEquals(30, rangeSlider.getValue());
    }

    @Test
    public void testSetValue() {
        rangeSlider.setValue(30);
        assertEquals(30, rangeSlider.getValue());

        rangeSlider.setValue(50);
        assertEquals(50, rangeSlider.getValue());
    }

    @Test
    public void testSetValueWithinBounds() {
        rangeSlider.setValue(90);
        assertEquals(50, rangeSlider.getValue());
        
        rangeSlider.setUpperValue(100);
        assertEquals(100, rangeSlider.getUpperValue());
    }

    @Test
    public void testGetUpperValue() {
        rangeSlider.setValue(30);
        rangeSlider.setUpperValue(70);
        assertEquals(70, rangeSlider.getUpperValue());
    }

    @Test
    public void testSetUpperValue() {
        rangeSlider.setValue(30);
        rangeSlider.setUpperValue(70);
        assertEquals(70, rangeSlider.getUpperValue());

        rangeSlider.setUpperValue(50);
        assertEquals(50, rangeSlider.getUpperValue());
    }

    @Test
    public void testSetUpperValueTooLow() {
        rangeSlider.setValue(30);
        rangeSlider.setUpperValue(20);
        assertEquals(30, rangeSlider.getUpperValue()); 
    }
}
