# ImageProcessing
An Image Processing API written using Java. This API currently has two classes which are the Pixel class and the Image class.

# Setup
This program was written using intellij idea community edition 15.0.2 and jdk 1.8.0_66. It also uses Junit to test the api.
It is recommended to use these systems to run the api program.

# Pixel class
The Pixel class contains an array with four integer values which are the alpha, red, green and blue pixel values.
This class also contains four getter and setter methods for the alpha, red, green and blue pixel values.

## Pixel methods

Pixel(int alpha, int red, int green, int blue) - Creates a new pixel object with the given values

getsAlpha() - Returns the alpha pixel value

setAlpha(int value) - Sets the alpha pixel value with the given value

getRed() - Returns the red pixel value

setRed(int value) - Sets the red pixel value with the given value

getGreen() - Returns the green pixel value

setGreen(int value) - Sets the green pixel value with the given value

getBlue() - Returns the blue pixel value

setBlue(int value) - Sets the blue pixel value with the given value

setPixel(int[] value) - Sets the pixel value


# Image class

The Image class contains a 2d array of Pixel objects. The Image class currently has nineteen methods which are the Image, writeImage,
getPixel, Width, Height, adjustBrightness, adjustContrast, adjustGamma, ColorFilter, Grayscale, Invert, Rotate, Crop, Repair, toYbCr,
toHSL, Resize, Sharpen and Blur.

## Image methods

Image(String file_name) - Creates a new image object with the given image file

writeImage(String file_name) - Writes the image object to the given file

getPixel(int i, int j) - Returns the pixel object at the given point

Width() - Returns the width of the image

Height() - Returns the height of the image

adjustBrightness(int NewBrightness) - Adjust the brightness of the image by the given value

adjustContrast(int NewContrast) - Adjust the contrast of the image by the given value

adjustGamma(double NewGamma) - Adjust the gamma of the image by the given value

ColorFilter(String Filter) - Filters the image by the given color ("Red", "Green" or "Blue")

Grayscale() - Transforms the image from color to grayscale

Invert() - Inverts the colors of the image

Rotate(int numberOfRotations) - Rotates the image 90 degrees the given number of times

Crop(int i, int j, int width, int height) - Crops the image starting at the given i and j values and going for the given width and height

Flip() - Flips the image horizontally

Repair() - Attempts to repair any dead pixels located in the image

toYCbCr() - Converts the image from RGB to YCbCr

toHSL() - Converts the image from RGB to HSL

Resize(int newWidth, int newHeight) - Resizes the image to the given width and height

Threshold(int thresholdAmount) - Thresholds the image using the given amount

Sharpen() - Attempts to sharpen the image

Blur() - Attempts to blur the image
