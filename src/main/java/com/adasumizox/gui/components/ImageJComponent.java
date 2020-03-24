package com.adasumizox.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Simple Component that will provide basic functionality of our app.
 * We theoretically can use basic JComponent for drawing but using our version
 * will make it easy and provide methods that we need
 * @version 0.1.0
 */
public class ImageJComponent extends JComponent {
    // We can propably do it better but i think that implementation is fine
    // Original image works mostly as placeholder
    private Image image = null;
    // Image that we work on
    private BufferedImage imageEffect = null;

    /**
     * Simple random image creation
     * @return Random image
     */
    private Image generateExampleImage() {
        int width = 300;
        int height = 300;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int a = (int)(Math.random()*256);
                int r = (int)(Math.random()*256);
                int g = (int)(Math.random()*256);
                int b = (int)(Math.random()*256);

                int p = (a<<24) | (r<<16) | (g<<8) | b;

                img.setRGB(x, y, p);
            }
        }
        return img;
    }

    /**
     * Simple getter that will return image that we hold in our component
     * @return Image that is currently in our component
     */
    public Image getImage() {
        return image;
    }

    /**
     * Simple setter that will set our Image to component
     * @param image image that we want to set in our component
     */
    public void setImage(Image image) {
        this.image = image;
        this.imageEffect = (BufferedImage) image;
        this.repaint();
    }

    /**
     * Simple getter that will return image that we are displaying in our component
     * @return BufferedImage that is currently displayed in our component
     */
    public BufferedImage getBufferedImage() {
        return imageEffect;
    }

    /**
     * Simple setter that will set BufferedImage
     * @param image BufferedImage that we want to display in our component
     */
    public void setBufferedImage(BufferedImage image) {
        this.imageEffect = image;
        this.repaint();
    }

    /**
     * This simple constructor for class com.adasumizox.gui.Components.ImageJComponent
     * @param image image that we want to set
     */
    ImageJComponent(Image image) {
        setImage(image);
    }
    /**
     * This simple constructor for class com.adasumizox.gui.Components.ImageJComponent without parameters
     * It declares image as a random image
     */
    public ImageJComponent() {
        setImage(generateExampleImage());
    }

    /**
     * Method inherited from javax.swing.JComponent
     * simple method for printing Graphics into our component.
     * We uses that to print our image on screen
     * @param g Graphics object
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageEffect, 0, 0, null);
    }
}