import org.jetbrains.annotations.NotNull;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class ItemChangeListener implements ItemListener {
    private Processing p = new Processing();
    private ImageJComponent component;

    ItemChangeListener(ImageJComponent comp) {
        this.component = comp;
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            String item = (String) event.getItem();
            switch(item) {
                case "Original":
                    component.setBufferedImage((BufferedImage) component.getImage());
                    break;
                case "Grayscale":
                    component.setBufferedImage(p.grayScale((BufferedImage) this.component.getImage()));
                    break;
                case "Blurred":
                    component.setBufferedImage(p.blur((BufferedImage) this.component.getImage()));
                    break;
                case "Binarization":
                    component.setBufferedImage(p.binarizationThreshold((BufferedImage) this.component.getImage(), 127));
                    break;
                case "MedianFilter":
                    component.setBufferedImage(p.medianFilter((BufferedImage) this.component.getImage()));
                    break;
                case "SharpenFilter":
                    component.setBufferedImage(p.sharpenFilter((BufferedImage) this.component.getImage()));
                    break;
                case "SobelFilter":
                    component.setBufferedImage(p.sobelFilter((BufferedImage) this.component.getImage()));
                    break;
                case "Dilatation":
                    component.setBufferedImage(p.imageDilatation((BufferedImage) this.component.getImage()));
                    break;
                case "Erosion":
                    component.setBufferedImage(p.imageErosion((BufferedImage) this.component.getImage()));
                    break;
            }
        }
    }
}