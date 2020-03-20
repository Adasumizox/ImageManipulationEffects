package com.adasumizox.gui.actions;

import com.adasumizox.gui.components.ImageJComponent;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple Action that saves file using JavaFileChooser
 * Action seperate functionality and state from components
 * Action is an action listener that provides not only action-event handling
 * We use it to change state of com.adasumizox.gui.Components.ImageJComponent
 * We usually bind actions to buttons and menu items
 * @version 0.1.0
 */
public class SaveAction extends AbstractAction {
    private static final Logger LOGGER = Logger.getLogger(SaveAction.class.getName());
    private ImageJComponent component;

    /**
     * This simple constructor for class com.adasumizox.gui.Actions.SaveAction
     * @param comp This is object of com.adasumizox.gui.Components.ImageJComponent that stores image
     * @param text Used for storing the String name for the action, used for a menu or button.
     * @param desc Used for storing a short String description for the action, used for tooltip text
     * @param mnemonic key that is used when we only use keyboard not mouse/ using this can make operating gui faster/ shorcuts
     */
    public SaveAction(ImageJComponent comp, String text, String desc, Integer mnemonic) {
        super(text);
        this.component = comp;
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }
    /**
     * method that will get image from component
     * @param comp com.adasumizox.gui.Components.ImageJComponent object that we will get image from
     */
    private Image getComponentImage(ImageJComponent comp) {
        return comp.getImage();
    }

    /**
     * method that will get our file extension
     * @param name Path that we want to get extension from
     */
    private String getFileExtension(String name) {
        int lastIndexOf = name.lastIndexOf('.');
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    /**
     * Method inherited from java.awt.event.ActionListener
     * It invokes when an action occurs e.g
     * It will choose a image file [JPG, PNG] from user and save content from component in it
     * @param e A semantic event which indicates that a component-defined action occurred
     */
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Save image");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter jpeg = new FileNameExtensionFilter("JPEG", "jpeg", "jpg");
        FileNameExtensionFilter png = new FileNameExtensionFilter("PNG", "png");
        jfc.addChoosableFileFilter(jpeg);
        jfc.addChoosableFileFilter(png);

        int returnValue = jfc.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                File file = new File(jfc.getSelectedFile().getAbsolutePath());
                //kind of workaround i guess we simply can return name.substring(lastIndexOf+1) from getFileExtension and avoid that substring but it seems fine
                String extension = getFileExtension(jfc.getSelectedFile().getAbsolutePath()).substring(1);
                BufferedImage image = (BufferedImage) getComponentImage(this.component);
                ImageIO.write(image, extension, file);
            } catch(IOException ex) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
    }
}
