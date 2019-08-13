import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Processing {
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

    private BufferedImage deepCopy(BufferedImage bufferedImage) {
        ColorModel cm = bufferedImage.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bufferedImage.copyData(null);

        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

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

}
