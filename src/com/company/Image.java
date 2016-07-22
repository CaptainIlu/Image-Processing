package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Image {
    private Pixel[][] image;

    public Image(String file_name) throws IOException {
        BufferedImage image_file = ImageIO.read(new File(file_name));
        image = readImage(image_file);
    }

    private static Pixel[][] readImage(BufferedImage image_file) {
        Pixel imageData[][] = new Pixel[image_file.getWidth()][image_file.getHeight()];
        int clr;
        int alpha, red, green, blue;

        for (int i = 0; i < image_file.getWidth(); i++) {
            for (int j = 0; j < image_file.getHeight(); j++) {
                clr = image_file.getRGB(i, j);
                alpha = (clr & 0xff0000) >> 24;
                red = (clr & 0x00ff0000) >> 16;
                green = (clr & 0x0000ff00) >> 8;
                blue = (clr & 0x000000ff);
                Pixel newPixel = new Pixel(alpha, red, green, blue);

                imageData[i][j] = newPixel;
            }
        }
        return imageData;
    }

    public void writeImage(String file_name) throws IOException {
        BufferedImage newImage = new BufferedImage(image.length, image[0].length, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                int rgb = (image[i][j].getAlpha() << 24) | (image[i][j].getRed() << 16) | (image[i][j].getGreen() << 8) | image[i][j].getBlue();
                newImage.setRGB(i, j, rgb);
            }
        }

        File newFile = new File(file_name);
        String fileExtension = file_name.substring(file_name.lastIndexOf('.') + 1);
        ImageIO.write(newImage, fileExtension, newFile);
    }

    public Pixel getPixel(int i, int j) {
        return image[i][j];
    }

    public int Width() {
        return image.length;
    }

    public int Height() {
        return image[0].length;
    }

    public void adjustBrightness(int NewBrightness) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                image[i][j].setBlue(image[i][j].getBlue() + NewBrightness);
                if (image[i][j].getBlue() > 255) {
                    image[i][j].setBlue(255);
                }
                if(image[i][j].getBlue() < 0) {
                    image[i][j].setBlue(1);
                }

                image[i][j].setGreen(image[i][j].getGreen() + NewBrightness);
                if (image[i][j].getGreen() > 255) {
                    image[i][j].setGreen(255);
                }
                if(image[i][j].getGreen() < 0) {
                    image[i][j].setGreen(1);
                }

                image[i][j].setRed(image[i][j].getRed() + NewBrightness);
                if (image[i][j].getRed() > 255) {
                    image[i][j].setRed(255);
                }
                if(image[i][j].getRed() < 0) {
                    image[i][j].setRed(1);
                }
            }
        }
    }

    public void adjustContrast(double NewContrast) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                double NewRed = (((((image[i][j].getRed() / 255.0) - 0.5) * NewContrast) + 0.5) * 255);
                double NewGreen = (((((image[i][j].getGreen() / 255.0) - 0.5) * NewContrast) + 0.5) * 255);
                double NewBlue = (((((image[i][j].getBlue() / 255.0) - 0.5) * NewContrast) + 0.5) * 255);

                image[i][j].setRed((int)NewRed);
                if (image[i][j].getRed() > 255) {
                    image[i][j].setRed(255);
                }
                if(image[i][j].getRed() < 0) {
                    image[i][j].setRed(1);
                }

                image[i][j].setGreen((int)NewGreen);
                if (image[i][j].getGreen() > 255) {
                    image[i][j].setGreen(255);
                }
                if(image[i][j].getGreen() < 0) {
                    image[i][j].setGreen(1);
                }

                image[i][j].setBlue((int)NewBlue);
                if (image[i][j].getBlue() > 255) {
                    image[i][j].setBlue(255);
                }
                if(image[i][j].getBlue() < 0) {
                    image[i][j].setBlue(1);
                }
            }
        }
    }

    public void adjustGamma(double NewGamma) {
        int Gamma[] = new int[256];
        for (int i = 0; i < 256; i++)
        {
            Gamma[i] = (int)Math.min(255, ((255.0 * Math.pow(i / 255.0, 1.0 / (NewGamma / 10))) + 0.5));
        }
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                image[i][j].setRed(Gamma[image[i][j].getRed()]);
                if (image[i][j].getRed() > 255) {
                    image[i][j].setRed(255);
                }
                if(image[i][j].getRed() < 0) {
                    image[i][j].setRed(1);
                }

                image[i][j].setGreen(Gamma[image[i][j].getGreen()]);
                if (image[i][j].getGreen() > 255) {
                    image[i][j].setGreen(255);
                }
                if(image[i][j].getGreen() < 0) {
                    image[i][j].setGreen(1);
                }

                image[i][j].setBlue(Gamma[image[i][j].getBlue()]);
                if (image[i][j].getBlue() > 255) {
                    image[i][j].setBlue(255);
                }
                if(image[i][j].getBlue() < 0) {
                    image[i][j].setBlue(1);
                }
            }
        }
    }

    public void ColorFilter(String Filter) {
        if (Filter.equals("Red")) {
            for (int i = 0; i < image.length; i++) {
                for(int j = 0; j < image[0].length; j++) {
                    image[i][j].setGreen(0);
                    image[i][j].setBlue(0);
                }
            }
        }
        if (Filter.equals("Green")) {
            for (int i = 0; i < image.length; i++) {
                for(int j = 0; j < image[0].length; j++) {
                    image[i][j].setRed(0);
                    image[i][j].setBlue(0);
                }
            }
        }
        if (Filter.equals("Blue")) {
            for (int i = 0; i < image.length; i++) {
                for(int j = 0; j < image[0].length; j++) {
                    image[i][j].setRed(0);
                    image[i][j].setGreen(0);
                }
            }
        }
    }

    public void Grayscale() {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                int NewColour = (int)(image[i][j].getBlue() * 0.114 + image[i][j].getGreen() * 0.587 + image[i][j].getRed() * 0.299);

                image[i][j].setBlue(NewColour);
                if (image[i][j].getBlue() > 255) {
                    image[i][j].setBlue(255);
                }
                if(image[i][j].getBlue() < 0) {
                    image[i][j].setBlue(1);
                }

                image[i][j].setGreen(NewColour);
                if (image[i][j].getGreen() > 255) {
                    image[i][j].setGreen(255);
                }
                if(image[i][j].getGreen() < 0) {
                    image[i][j].setGreen(1);
                }

                image[i][j].setRed(NewColour);
                if (image[i][j].getRed() > 255) {
                    image[i][j].setRed(255);
                }
                if(image[i][j].getRed() < 0) {
                    image[i][j].setBlue(1);
                }
            }
        }
    }

    public void Invert() {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {

                image[i][j].setBlue(255-image[i][j].getBlue());
                if (image[i][j].getBlue() > 255) {
                    image[i][j].setBlue(255);
                }
                if(image[i][j].getBlue() < 0) {
                    image[i][j].setBlue(1);
                }

                image[i][j].setGreen(255-image[i][j].getGreen());
                if (image[i][j].getGreen() > 255) {
                    image[i][j].setGreen(255);
                }
                if(image[i][j].getGreen() < 0) {
                    image[i][j].setGreen(1);
                }

                image[i][j].setRed(255-image[i][j].getRed());
                if (image[i][j].getRed() > 255) {
                    image[i][j].setRed(255);
                }
                if(image[i][j].getRed() < 0) {
                    image[i][j].setBlue(1);
                }
            }
        }
    }

    public void Rotate(int numberOfRotations) {
        for (int m = 0; m < numberOfRotations; m++) {
            Pixel[][] newImage = new Pixel[image[0].length][image.length];
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    newImage[image[0].length - 1 - j][i] = image[i][j];
                }
            }
            image = newImage;
        }
    }

    public void Crop(int i, int j, int width, int height) {
        Pixel[][] newImage = new Pixel[width][height];
        for (int m = 0; m < width; m++) {
            for (int n = 0; n < height; n++) {
                newImage[m][n] = image[i+m][j+n];
            }
        }
        image = newImage;
    }

    public void Flip() {
        Pixel[][] newImage = new Pixel[image.length][image[0].length];
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                newImage[i][j] = image[image.length-i-1][j];
            }
        }
        image = newImage;
    }

    public void Repair() {
        int[] deadPixel = new int[]{0, 0, 0, 0};
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if (Arrays.equals(image[i][j].getPixel(),deadPixel)) {
                    Pixel[][] ImageDist = ImDist(i, j, 5);
                    Pixel ReconPixel = ImColour(ImageDist, i, j, 5);
                    image[i][j] = ReconPixel;
                }
            }
        }
    }

    public void toYCbCr() {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                int NewBlue = (int)(image[i][j].getBlue() * -0.071 + image[i][j].getGreen() * -0.368 + image[i][j].getRed() * 0.439 + 128);
                int NewGreen = (int)(image[i][j].getBlue() * 0.439 + image[i][j].getGreen() * -0.291 + image[i][j].getRed() * -0.148 + 128);
                int NewRed = (int)(image[i][j].getBlue() * 0.098 + image[i][j].getGreen() * 0.504 + image[i][j].getRed() * 0.257 + 16);

                image[i][j].setBlue(NewBlue);
                if (image[i][j].getBlue() > 255) {
                    image[i][j].setBlue(255);
                }
                if(image[i][j].getBlue() < 0) {
                    image[i][j].setBlue(1);
                }

                image[i][j].setGreen(NewGreen);
                if (image[i][j].getGreen() > 255) {
                    image[i][j].setGreen(255);
                }
                if(image[i][j].getGreen() < 0) {
                    image[i][j].setGreen(1);
                }

                image[i][j].setRed(NewRed);
                if (image[i][j].getRed() > 255) {
                    image[i][j].setRed(255);
                }
                if(image[i][j].getRed() < 0) {
                    image[i][j].setRed(1);
                }
            }
        }
    }

    public void toHSL() {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                int[] values = {image[i][j].getRed(), image[i][j].getGreen(), image[i][j].getBlue()};
                Arrays.sort(values);

                double min = values[0]/255.0;
                double max = values[2]/255.0;

                double L = (min + max)/2;
                double S;
                double H;

                if (L < 0.5) {
                    S = (max-min)/(max+min);
                }
                else {
                    S = (max-min)/(2.0-max-min);
                }

                if (max == image[i][j].getRed()/255.0) {
                    H = ((image[i][j].getGreen()/255.0)-(image[i][j].getBlue()/255.0))/(max-min);
                }
                else if (max == (image[i][j].getGreen()/255.0)) {
                    H = 2.0 + ((image[i][j].getBlue()/255.0)-(image[i][j].getRed()/255.0))/(max-min);
                }
                else {
                    H = 4.0 + ((image[i][j].getRed()/255.0)-(image[i][j].getGreen()/255.0))/(max-min);
                }
                H*=60.0;

                image[i][j].setRed((int)(H*255/360));
                if (image[i][j].getRed() > 255) {
                    image[i][j].setRed(255);
                }
                if(image[i][j].getRed() < 0) {
                    image[i][j].setRed(1);
                }

                image[i][j].setGreen((int)(S*255));
                if (image[i][j].getGreen() > 255) {
                    image[i][j].setGreen(255);
                }
                if(image[i][j].getGreen() < 0) {
                    image[i][j].setGreen(1);
                }

                image[i][j].setBlue((int)(L*255));
                if (image[i][j].getBlue() > 255) {
                    image[i][j].setBlue(255);
                }
                if(image[i][j].getBlue() < 0) {
                    image[i][j].setBlue(1);
                }
            }
        }
    }

    public void Resize(int newWidth, int newHeight) {
        Pixel[][] newImage = new Pixel[newWidth][newHeight];

        for (int i = 0; i < newImage.length; i++){
            for (int j = 0; j < newImage[0].length; j++){
                double m = (i/((double)newImage.length/(double)image.length));
                double n = (j/((double)newImage[0].length/(double)image[0].length));
                newImage[i][j] = image[(int)(m)][(int)(n)];
            }
        }

        image = newImage;
    }

    public void Threshold(int thresholdAmount) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[0].length; j++) {
                if(image[i][j].getRed() < thresholdAmount && image[i][j].getGreen() < thresholdAmount && image[i][j].getBlue() < thresholdAmount) {
                    image[i][j].setRed(0);
                    image[i][j].setGreen(0);
                    image[i][j].setBlue(0);
                } else {
                    image[i][j].setRed(255);
                    image[i][j].setGreen(255);
                    image[i][j].setBlue(255);
                }
            }
        }
    }

    public void Sharpen() {
        int filterWidth = 3;
        int filterHeight = 3;

        double[][] filter = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
        int factor = 1;
        int bias = 0;

        Pixel[][] newImage = new Pixel[image.length][image[0].length];

        for (int x = 0; x < image.length; ++x)
        {
            for (int y = 0; y < image[0].length; ++y)
            {
                int red = 0, green = 0, blue = 0;

                for (int filterX = 0; filterX < filterWidth; filterX++)
                {
                    for (int filterY = 0; filterY < filterHeight; filterY++)
                    {
                        int imageX = (x - filterWidth / 2 + filterX + image.length) % image.length;
                        int imageY = (y - filterHeight / 2 + filterY + image[0].length) % image[0].length;

                        int[] imageColor = image[imageX][imageY].getPixel();

                        red += imageColor[1] * filter[filterX][filterY];
                        green += imageColor[2] * filter[filterX][filterY];
                        blue += imageColor[3] * filter[filterX][filterY];
                    }
                    int r = Math.min(Math.max((factor * red + bias), 0), 255);
                    int g = Math.min(Math.max((factor * green + bias), 0), 255);
                    int b = Math.min(Math.max((factor * blue + bias), 0), 255);

                    newImage[x][y] = new Pixel(image[x][y].getAlpha(), r, g, b);
                }
            }
        }
        image = newImage;
    }

    public void Blur() {
        double[][] filter = {{0.111, 0.111, 0.111}, {0.111, 0.111, 0.111}, {0.111, 0.111, 0.111}};
        double sum_r, sum_g, sum_b;
        int x1, y1;
        Pixel[][] newImage = new Pixel[image.length][image[0].length];

        for(int i = 0; i < image.length; i++) {
            for(int j = 0; j < image[0].length; j++) {
                sum_r = sum_g = sum_b = 0.0;
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        x1 = circular(image[0].length, j-n);
                        y1 = circular(image.length, i-m);
                        sum_r += filter[n+1][m+1]*image[y1][x1].getRed();
                        sum_g += filter[n+1][m+1]*image[y1][x1].getGreen();
                        sum_b += filter[n+1][m+1]*image[y1][x1].getBlue();
                    }
                }
                newImage[i][j] = new Pixel(0, (int)sum_r, (int)sum_g, (int)sum_b);
            }
        }
        image = newImage;
    }

    private int circular(int M, int x) {
        if (x<0) {
            return x+M;
        }
        if(x >= M) {
            return x-M;
        }
        return x;
    }

    private Pixel[][] ImDist(int i, int j, int k) {
        int w = i; int y = i; int x = j; int z = j; int l = 0;
        Pixel ImageDist[][] = null;

        while(l <= k) {
            l = 0;
            if (w > 0) {
                w = w - 1;
            }
            if (x > 0) {
                x = x - 1;
            }
            if (y < image.length) {
                y = y + 1;
            }
            if (z < image[0].length) {
                z = z + 1;
            }

            ImageDist = new Pixel[y - w - 1][z - x - 1];
            for (int a = w, b = 0; a < y - 1; a++, b++) {
                for (int c = x, d = 0; c < z - 1; c++, d++) {
                    ImageDist[b][d] = image[a][c];
                }
            }
            for (int m = 0; m < ImageDist.length; m++) {
                for (int n = 0; n < ImageDist[0].length; n++) {
                    if (ImageDist[m][n].getRed() > 0 || ImageDist[m][n].getGreen() > 0 || ImageDist[m][n].getBlue() > 0) {
                        l += 1;
                    }
                }
            }
        }

        return ImageDist;
    }

    private Pixel ImColour(Pixel[][] ImageDist, int i, int j, int k) {
        int p = 0; int D;
        int[][] C = new int[k][4];
        Pixel ReconPixel = new Pixel(0, 0, 0, 0);

        for (int m = 0; m < ImageDist.length; m++) {
            for (int n = 0; n < ImageDist[0].length; n++) {
                if (ImageDist[m][n].getRed() > 0 || ImageDist[m][n].getGreen() > 0 || ImageDist[m][n].getBlue() > 0) {
                    if (p < k) {
                        C[p][0] = ImageDist[m][n].getRed();
                        C[p][1] = ImageDist[m][n].getGreen();
                        C[p][2] = ImageDist[m][n].getBlue();
                        C[p][3] = Math.abs(i - m) + Math.abs(j - n);
                        ReconPixel = Colour(C, k);
                        p+=1;
                    }
                }
            }
        }
        return ReconPixel;
    }

    private Pixel Colour(int[][] C, int k) {
        Pixel ReconPixel = new Pixel(0, 0, 0, 0);
        for (int i = 0; i < 3; i++) {
            int CSum = 0;
            for (int j = 0; j < k; j++) {
                CSum += C[j][i];
                if (j == k - 1) {
                    switch (i) {
                        case 0: ReconPixel.setRed(CSum/k);
                                break;
                        case 1: ReconPixel.setGreen(CSum/k);
                                break;
                        case 2: ReconPixel.setBlue(CSum/k);
                                break;
                    }
                }
            }
        }
        return ReconPixel;
    }
}