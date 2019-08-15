import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

class Processing {
    BufferedImage binarizationThreshold(BufferedImage bufferedImage, int threshold) {
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

    private BufferedImage deepCopy(BufferedImage bufferedImage) {
        ColorModel cm = bufferedImage.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bufferedImage.copyData(null);

        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    BufferedImage grayScale(BufferedImage bufferedImage) {
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

    BufferedImage blur(BufferedImage input) {
        BufferedImage imageWorkedOn = deepCopy(input);
        for(int h = 1; h < imageWorkedOn.getHeight() - 1; ++h){
            for(int w = 1; w < imageWorkedOn.getWidth() - 1; ++w){
                double red = 0, green = 0, blue = 0;
                for(int currenth = h - 1; currenth < h + 2; ++currenth){
                    for(int currentw = w - 1; currentw < w + 2; ++currentw) {
                        Color c = new Color(input.getRGB(currentw, currenth));
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
                imageWorkedOn.setRGB(w, h, finalColor.getRGB());
            }
        }
        return imageWorkedOn;
    }

}
