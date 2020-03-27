package com.adasumizox.gui.actions;

import com.adasumizox.database.Database;
import com.adasumizox.gui.components.ImageJComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

/**
 * Simple Action that saves image to database
 * Action seperate functionality and state from components
 * Action is an action listener that provides not only action-event handling
 * We use it to change state of com.adasumizox.gui.Components.ImageJComponent
 * We usually bind actions to buttons and menu items
 * @version 0.1.0
 */
public class SaveDBAction extends AbstractAction {
    private static final Logger LOGGER = Logger.getLogger(SaveAction.class.getName());
    private ImageJComponent component;

    /**
     * This simple constructor for class com.adasumizox.gui.Actions.SaveDBAction
     * @param comp This is object of com.adasumizox.gui.Components.ImageJComponent that stores image
     * @param text Used for storing the String name for the action, used for a menu or button.
     * @param desc Used for storing a short String description for the action, used for tooltip text
     * @param mnemonic key that is used when we only use keyboard not mouse/ using this can make operating gui faster/ shorcuts
     */
    public SaveDBAction(ImageJComponent comp, String text, String desc, Integer mnemonic) {
        super(text);
        this.component = comp;
        putValue(SHORT_DESCRIPTION, desc);
        putValue(MNEMONIC_KEY, mnemonic);
    }

    /**
     * Method inherited from java.awt.event.ActionListener
     * It invokes when an action occurs e.g
     * It will choose a image file [JPG, PNG] from user and save content from component in it
     * @param e A semantic event which indicates that a component-defined action occurred
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Database d = Database.getInstance();
        // TODO: Add component that will take data from user
        d.insertImage(d.getConnection(), "temp", "temp", ".jpg" , (BufferedImage) component.getImage());
    }
}
