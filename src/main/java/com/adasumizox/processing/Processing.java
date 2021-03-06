package com.adasumizox.processing;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.Arrays;
/**
 * com.adasumizox.processing.Processing class that provides us with method that will allow us to apply effects on image.
 * Class should be static that would been easier.
 * @version 0.1.0
 */
public class Processing {
    /**
     * complicated method that will copy image to avoid overwriting original image
     * @param bufferedImage image that we want to deepCopy
     * @return deep copy of BufferedImage
     */
    private BufferedImage deepCopy(BufferedImage bufferedImage) {
        ColorModel cm = bufferedImage.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bufferedImage.copyData(null);

        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }


    /**
     * complicated method that will apply blur effect to image simply applying 1/9 * currentColor for every
     * 3x3 matrix. It's complicated
     * @param bufferedImage image that we want to applyEffect
     * @return BufferedImage with effect
     */
    public BufferedImage blur(BufferedImage bufferedImage) {
        BufferedImage newImage = deepCopy(bufferedImage);
        for(int h = 1; h < newImage.getHeight() - 1; ++h){
            for(int w = 1; w < newImage.getWidth() - 1; ++w){
                double red = 0;
                double green = 0;
                double blue = 0;
                for(int currenth = h - 1; currenth < h + 2; ++currenth){
                    for(int currentw = w - 1; currentw < w + 2; ++currentw) {
                        Color c = new Color(bufferedImage.getRGB(currentw, currenth));
                        red += c.getRed();
                        green += c.getGreen();
                        blue += c.getBlue();
                    }
                }
                red = red / 9;
                green = green / 9;
                blue = blue / 9;
                int redFinal = (int) red;
                int greenFinal = (int) green;
                int blueFinal = (int) blue;
                Color finalColor = new Color(redFinal, greenFinal, blueFinal);
                newImage.setRGB(w, h, finalColor.getRGB());
            }
        }
        return newImage;
    }

    /**
     * complicated method that will apply medianFilter effect to image simply RGB value of median(half of array) for every
     * 3x3 matrix. It's complicated
     * @param bufferedImage image that we want to applyEffect
     * @return BufferedImage with effect
     */
    public BufferedImage medianFilter(BufferedImage bufferedImage) {
        BufferedImage newImage = deepCopy(bufferedImage);

        for(int h = 1; h < newImage.getHeight() - 1; ++h) {
            for(int w = 1; w < newImage.getWidth() - 1; ++w) {
                int[] values = new int[9];
                int index = 0;
                for(int currenth = h - 1; currenth < h + 2; ++currenth){
                    for(int currentw = w - 1; currentw < w + 2; ++currentw){
                        values[index] = bufferedImage.getRGB(currentw, currenth);
                        ++index;
                    }
                }
                Arrays.sort(values);
                int mediana = values[4];
                newImage.setRGB(w, h, mediana);
            }
        }
        return newImage;
    }

    /**
     * complicated method that will apply sharpenFilter effect to image
     * @param bufferedImage image that we want to applyEffect
     * @return BufferedImage with effect
     */
    public BufferedImage sharpenFilter(BufferedImage bufferedImage){
        BufferedImage newImage = deepCopy(bufferedImage);
        for(int h = 1; h < newImage.getHeight() - 1; ++h){
            for(int w = 1; w <newImage.getWidth() - 1; ++w){
                Color up = new Color(bufferedImage.getRGB(w, h - 1));
                Color left = new Color(bufferedImage.getRGB(w - 1, h));
                Color right = new Color(bufferedImage.getRGB(w + 1, h));
                Color down = new Color(bufferedImage.getRGB(w, h + 1));

                Color center = new Color(bufferedImage.getRGB(w, h));

                int red = 5 * center.getRed() - up.getRed();
                red = red - down.getRed();
                red = red - left.getRed() - right.getRed();
                int green = 5 * center.getGreen() - up.getGreen();
                green = green - down.getGreen();
                green = green - left.getGreen() - right.getGreen();
                int blue = 5 * center.getBlue() - up.getBlue();
                blue = blue - down.getBlue();
                blue = blue - left.getBlue() - right.getBlue();

                if(red > 255) {
                    red = 255;
                } else if(red < 0) {
                    red = 0;
                }

                if(green > 255){
                    green = 255;
                } else if(green < 0) {
                    green = 0;
                }

                if(blue > 255) {
                    blue = 255;
                } else if(blue < 0) {
                    blue = 0;
                }

                newImage.setRGB(w, h, new Color(red, green, blue).getRGB());
            }
        }
        return newImage;
    }

    /**
     * complicated method that will apply grayScale effect to image by choosing one of RGB value in our case Red
     * and apllying it for every green and blue value for every pixel
     * @param bufferedImage image that we want to applyEffect
     * @return BufferedImage with effect
     */
    public BufferedImage grayScale(BufferedImage bufferedImage) {
        BufferedImage newImage = deepCopy(bufferedImage);
        for(int h = 0; h < bufferedImage.getHeight(); ++h) {
            for(int w = 0; w < bufferedImage.getWidth(); ++w) {
                Color c = new Color(bufferedImage.getRGB(w, h));
                int red = c.getRed();
                newImage.setRGB(w, h, new Color(red, red, red).getRGB());
            }
        }
        return newImage;
    }

    /**
     * complicated method that will apply sobelFilter effect to image.
     * @param bufferedImage image that we want to applyEffect
     * @return BufferedImage with effect
     */
    public BufferedImage sobelFilter(BufferedImage bufferedImage){
        BufferedImage newImage = deepCopy(bufferedImage);
        newImage = grayScale(newImage);

        for(int h = 1; h < bufferedImage.getHeight() - 1; ++h) {
            for(int w = 1; w < bufferedImage.getWidth() - 1; ++w) {

                Color leftDown = new Color(bufferedImage.getRGB(w + 1, h - 1));
                Color centerDown = new Color(bufferedImage.getRGB(w + 1, h));
                Color rightDown = new Color(bufferedImage.getRGB(w + 1, h + 1));

                Color leftUp = new Color(bufferedImage.getRGB(w - 1, h - 1));
                Color centerUp = new Color(bufferedImage.getRGB(w - 1, h));
                Color rightUp = new Color(bufferedImage.getRGB(w - 1, h + 1));

                Color rightCenter = new Color(bufferedImage.getRGB(w, h + 1));
                Color leftCenter = new Color(bufferedImage.getRGB(w, h - 1));

                int x = leftDown.getRed();
                x = x + 2 * centerDown.getRed();
                x = x + rightDown.getRed();
                x -= leftUp.getRed();
                x = x - 2 * centerUp.getRed();
                x = x - rightUp.getRed();

                int y = rightUp.getRed();
                y = y + 2 * rightCenter.getRed();
                y = y + rightDown.getRed();

                y -= leftUp.getRed();
                y = y - 2 * leftCenter.getRed();
                y = y + leftDown.getRed();

                double x2 = Math.pow(x, 2);
                double y2 = Math.pow(y, 2);
                double x2y2 = x2+y2;

                double rgb = Math.sqrt(x2y2);
                if(rgb > 255) rgb = 255;
                int finalRgb = (int) rgb;
                Color finalColor = new Color(finalRgb, finalRgb, finalRgb);
                newImage.setRGB(w, h, finalColor.getRGB());
            }
        }
        return newImage;
    }

    /**
     * complicated method that will apply binarization effect to image with certain Threshold.
     * It takes sum of values from RGB of pixel and divide it by 3. We will get result if it is less than threshold
     * we choose black color if it is more we just simply choose white
     * @param bufferedImage image that we want to applyEffect
     * @return BufferedImage with effect
     */
    public BufferedImage binarizationThreshold(BufferedImage bufferedImage, int threshold) {
        BufferedImage newImage = deepCopy(bufferedImage);
        for(int h = 0; h < bufferedImage.getHeight(); ++h) {
            for(int w = 0; w < bufferedImage.getWidth(); ++w) {
                Color c = new Color(bufferedImage.getRGB(w,h));
                int green = c.getGreen();
                int red = c.getRed();
                int blue = c.getBlue();

                double result = ((double)(red + green + blue)) / 3;
                green = (int)result;

                if(green < threshold) {
                    newImage.setRGB(w, h, Color.BLACK.getRGB());
                } else {
                    newImage.setRGB(w, h, Color.WHITE.getRGB());
                }
            }
        }
        return newImage;
    }

    /**
     * complicated method that will apply imageDilatation effect to image. It is recommended to use it with binarized image.
     * It will simply increase/decrease edges of binarized image.
     * @param bufferedImage image that we want to applyEffect
     * @return BufferedImage with effect
     */
    public BufferedImage imageDilatation(BufferedImage bufferedImage) {
        BufferedImage newImage = deepCopy(bufferedImage);

        for(int h = 1; h < bufferedImage.getHeight() - 1; ++h){
            for(int w = 1; w < bufferedImage.getWidth() - 1; ++w) {
                for(int i = -1; i < 2; ++i) {
                    for(int j = -1; j < 2; ++j) {
                        if(i != 0 && j != 0) {
                            Color currentColor = new Color(bufferedImage.getRGB(w + i, h + j));
                            if(currentColor.getRGB() == Color.BLACK.getRGB()) {
                                newImage.setRGB(w, h, Color.BLACK.getRGB());
                                break;
                            }
                        }
                    }
                }
            }
        }
        return newImage;
    }

    /**
     * complicated method that will apply imageErosion effect to image. It is recommended to use it with binarized image.
     * It will simply increase/decrease edges of binarized image.
     * @param bufferedImage image that we want to applyEffect
     * @return BufferedImage with effect
     */
    public BufferedImage imageErosion(BufferedImage bufferedImage) {
        BufferedImage newImage = deepCopy(bufferedImage);

        for(int h = 1; h < bufferedImage.getHeight() - 1; ++h){
            for(int w = 1; w < bufferedImage.getWidth() - 1; ++w) {
                for(int i = -1; i < 2; ++i) {
                    for(int j = -1; j < 2; ++j) {
                        if(i != 0 && j != 0){
                            Color currentColor = new Color(bufferedImage.getRGB(w + i, h + j));
                            if(currentColor.getRGB() == Color.WHITE.getRGB()) {
                                newImage.setRGB(w, h, Color.WHITE.getRGB());
                                break;
                            }
                        }
                    }
                }
            }
        }
        return newImage;
    }
}