package com.adasumizox.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import com.adasumizox.gui.actions.OpenAction;
import com.adasumizox.gui.actions.SaveAction;
import com.adasumizox.gui.components.*;
import com.adasumizox.gui.listeners.ItemChangeListener;

//TODO: Add slider for binarization percent
/**
 * Main com.adasumizox.gui.Gui class that creates entire user interface.
 * @version 0.1.0
 */
public class Gui {
    // effect implemented that we can use. If we create more we can simply add others to it.
    protected static final String[] IMPLEMENTED_EFFECTS = {"Original","Grayscale","Blurred","Binarization", "MedianFilter", "SharpenFilter", "SobelFilter", "Dilatation", "Erosion"};
    public static void main(String... args) {
        // creating frame and setting default width and height of it
        JFrame frame = new JFrame("Image manipulation");
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        ImageJComponent imageComponent = new ImageJComponent();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        Action openAction = new OpenAction(imageComponent ,"Open", "This is a file open menu option", KeyEvent.VK_O);
        JMenuItem menuItemOpen = new JMenuItem(openAction);
        Action saveAction = new SaveAction(imageComponent, "Save", "This is a file save menu option", KeyEvent.VK_S);
        JMenuItem menuItemSave = new JMenuItem(saveAction);
        fileMenu.add(menuItemOpen);
        fileMenu.add(menuItemSave);

        JPanel footer = new JPanel();
        JLabel label = new JLabel("Choose effect");
        JComboBox<String> effects = new JComboBox<>(IMPLEMENTED_EFFECTS);
        effects.addItemListener(new ItemChangeListener(imageComponent));
        Action applyAction = new com.adasumizox.gui.actions.ApplyAction(imageComponent, "Apply", "This is button for saving effects", KeyEvent.VK_A);
        JButton applyBtn = new JButton(applyAction);
        footer.add(label);
        footer.add(effects);
        footer.add(applyBtn);

        frame.getContentPane().add(BorderLayout.NORTH, menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, imageComponent);
        frame.getContentPane().add(BorderLayout.SOUTH, footer);
        frame.setVisible(true);
    }
}