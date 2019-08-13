import javax.swing.*;
import java.awt.*;

class ImageJComponent extends JComponent {
    private Image image;

    public Image getImage() {
        return image;
    }

    void setImage(Image image) {
        this.image = image;
        this.repaint();
    }

    ImageJComponent(Image image) {
        setImage(image);
    }

    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
