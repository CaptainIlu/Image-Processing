package com.company;

public class Pixel {
    private int[] pixel = new int[4];

    public Pixel(int alpha, int red, int green, int blue) {
        pixel[0] = alpha;
        pixel[1] = red;
        pixel[2] = green;
        pixel[3] = blue;
    }

    public int getAlpha() {
        return pixel[0];
    }

    public void setAlpha(int value) {
        pixel[0] = value;
    }

    public int getRed() {
        return pixel[1];
    }

    public void setRed(int value) {
        pixel[1] = value;
    }

    public int getGreen() {
        return pixel[2];
    }

    public void setGreen(int value) {
        pixel[2] = value;
    }

    public int getBlue() {
        return pixel[3];
    }

    public void setBlue(int value) {
        pixel[3] = value;
    }

    public int[] getPixel() {
        return pixel;
    }

    public void setPixel(int[] value) {
        pixel = value;
    }
}
