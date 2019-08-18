import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

class ImageJComponent extends JComponent {
    private Image image = null;
    private BufferedImage imageEffect = null;

    Image getImage() {
        return image;
    }

    void setImage(Image image) {
        this.image = image;
        this.imageEffect = (BufferedImage) image;
        this.repaint();
    }

    BufferedImage getBufferedImage() {
        return imageEffect;
    }

    void setBufferedImage(BufferedImage image) {
        this.imageEffect = image;
        this.repaint();
    }

    ImageJComponent(Image image) {
        setImage(image);
    }

    public void paint(Graphics g) {
        g.drawImage(imageEffect, 0, 0, null);
    }
}
