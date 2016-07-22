package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PixelTest {
    public Pixel pixel;

    @Before
    public void setUp() throws Exception {
        pixel = new Pixel(0, 29, 19, 10);
    }

    @Test
    public void testGetAlpha() throws Exception {
        int alpha = pixel.getAlpha();
        assertEquals(alpha, 0);
    }

    @Test
    public void testSetAlpha() throws Exception {
        pixel.setAlpha(255);
        int alpha = pixel.getAlpha();
        assertEquals(alpha, 255);
    }

    @Test
    public void testGetRed() throws Exception {
        int red = pixel.getRed();
        assertEquals(red, 29);
    }

    @Test
    public void testSetRed() throws Exception {
        pixel.setRed(100);
        int red = pixel.getRed();
        assertEquals(red, 100);
    }

    @Test
    public void testGetGreen() throws Exception {
        int green = pixel.getGreen();
        assertEquals(green, 19);
    }

    @Test
    public void testSetGreen() throws Exception {
        pixel.setGreen(100);
        int green = pixel.getGreen();
        assertEquals(green, 100);
    }

    @Test
    public void testGetBlue() throws Exception {
        int blue = pixel.getBlue();
        assertEquals(blue, 10);
    }

    @Test
    public void testSetBlue() throws Exception {
        pixel.setBlue(100);
        int blue = pixel.getBlue();
        assertEquals(blue, 100);
    }

    @Test
    public void testGetPixel() throws Exception {
        int[] expectedPixel = pixel.getPixel();
        int[] actualPixel = new int[] {0, 29, 19, 10};
        assertArrayEquals(expectedPixel, actualPixel);
    }

    @Test
    public void testSetPixel() throws Exception {
        int[] actualPixel = new int[] {0, 100, 125, 150};
        pixel.setPixel(actualPixel);
        int[] expectedPixel = pixel.getPixel();
        assertArrayEquals(expectedPixel, actualPixel);
    }
}