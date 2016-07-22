package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;

import static org.junit.Assert.*;

public class ImageTest {
    public Image image;

    @Before
    public void setUp() throws Exception {
        String projectDir = System.getProperty("user.dir");
        image = new Image(projectDir + "\\src\\com\\company\\Test.jpg");
    }

    @After
    public void tearDown() throws Exception {
        File file = new File("Test2.jpg");
        file.delete();
    }

    @Test
    public void testwriteImage() throws Exception {
        image.writeImage("Test2.jpg");
        File file = new File("Test2.jpg");
        assertTrue(file.exists());
    }

    @Test
    public void testGetPixel() throws Exception {
        Pixel expectedPixel = image.getPixel(0, 0);
        int[] actualPixel = new int[]{0, 46, 56, 21};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testWidth() throws Exception {
        int width = image.Width();
        assertEquals(width, 275);
    }

    @Test
    public void testHeight() throws Exception {
        int height = image.Height();
        assertEquals(height, 183);
    }

    @Test
    public void testadjustBrightness() throws Exception {
        image.adjustBrightness(25);
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 71, 81, 46};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testadjustContrast() throws Exception {
        image.adjustContrast(12.0);
        Pixel expectedPixel = image.getPixel(100,100);
        int[] actualPixel = new int[] {0, 1, 1, 25};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testadjustGamma() throws Exception {
        image.adjustGamma(12.0);
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 61, 72, 32};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testColorFilterRed() throws Exception {
        image.ColorFilter("Red");
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 46, 0, 0};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testColorFilterGreen() throws Exception {
        image.ColorFilter("Green");
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 0, 56, 0};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testColorFilterBlue() throws Exception {
        image.ColorFilter("Blue");
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 0, 0, 21};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testGrayscale() throws Exception {
        image.Grayscale();
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 49, 49, 49};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testInvert() throws Exception {
        image.Invert();
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 209,199,234};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testRotate90() throws Exception {
        image.Rotate(1);
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 78, 116, 33};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testRotate180() throws Exception {
        image.Rotate(2);
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 123, 155, 70};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testRotate270() throws Exception {
        image.Rotate(3);
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 137, 89, 163};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testRotate360() throws Exception {
        image.Rotate(4);
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 46, 56, 21};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testCrop() throws Exception {
        Pixel actualPixel = image.getPixel(50,50);
        image.Crop(50, 50, 25, 25);
        Pixel expectedPixel = image.getPixel(0,0);
        assertArrayEquals(expectedPixel.getPixel(), actualPixel.getPixel());
        assertEquals(image.Width(), 25);
    }

    @Test
    public void testFlip() throws Exception {
        Pixel actualPixel = image.getPixel(0,0);
        image.Flip();
        Pixel expectedPixel = image.getPixel(image.Width()-1, 0);
        assertArrayEquals(expectedPixel.getPixel(), actualPixel.getPixel());
    }

    @Test
    public void testRepair() throws Exception {
        int[] deadPixel = new int[] {0, 0, 0, 0};
        image.getPixel(0,0).setPixel(deadPixel);

        image.Repair();
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 43, 51, 18};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testtoYCbCr() throws Exception {
        image.toYCbCr();
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 58, 114, 126};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testtoHSL() throws Exception {
        image.toHSL();
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 54, 115, 38};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testResize() throws Exception {
        image.Resize(50,50);
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 46, 56, 21};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
        assertEquals(image.Width(), 50);
        assertEquals(image.Height(), 50);
    }

    @Test
    public void testThreshold() throws Exception {
        image.Threshold(50);
        Pixel expectedPixel = image.getPixel(0,0);
        int[] actualPixel = new int[] {0, 255, 255, 255};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testSharpen() throws Exception {
        image.Sharpen();
        Pixel expectedPixel = image.getPixel(50, 50);
        int[] actualPixel = new int[] {0, 97, 99, 101};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }

    @Test
    public void testBlur() throws Exception {
        image.Blur();
        Pixel expectedPixel = image.getPixel(50, 50);
        int[] actualPixel = new int[] {0, 96, 97, 102};
        assertArrayEquals(expectedPixel.getPixel(), actualPixel);
    }
}