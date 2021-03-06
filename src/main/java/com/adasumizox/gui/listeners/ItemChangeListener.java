package com.adasumizox.gui.listeners;

import com.adasumizox.gui.components.ImageJComponent;
import com.adasumizox.processing.Processing;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

/**
 * Simple ItemListener that will help us with selecting effects from our comboBox
 * It's probably the best way to handle events when user click on option
 * @version 0.1.0
 */
public class ItemChangeListener implements ItemListener {
    // class object that holds methods providing effects that we will use
    // should be static
    private Processing p = new Processing();
    private ImageJComponent component;

    /**
     * This simple constructor for class com.adasumizox.gui.Listeners.ItemChangeListener
     * @param comp This is object of com.adasumizox.gui.Components.ImageJComponent that stores image
     */
    public ItemChangeListener(ImageJComponent comp) {
        this.component = comp;
    }

    /**
     * Simple method inherited from java.awt.event.ItemListener
     * @param event ItemEvent event that will come when user select some option
     */
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
                default:
                    component.setBufferedImage((BufferedImage) component.getImage());
                    break;
            }
        }
    }
}