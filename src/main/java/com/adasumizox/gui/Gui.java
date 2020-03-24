package com.adasumizox.gui;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;


import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.lang.reflect.InvocationTargetException;

import com.adasumizox.gui.actions.OpenAction;
import com.adasumizox.gui.actions.SaveAction;
import com.adasumizox.gui.components.*;
import com.adasumizox.gui.listeners.ItemChangeListener;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;

import static javax.swing.SwingUtilities.invokeAndWait;

/**
 * Main com.adasumizox.gui.Gui class that creates entire user interface.
 * @version 0.1.0
 */
public class Gui extends GuiFrame{
    private static final long serialVersionUID = 1L;
    protected static final String[] IMPLEMENTED_EFFECTS = {"Original","Grayscale","Blurred","Binarization", "MedianFilter", "SharpenFilter", "SobelFilter", "Dilatation", "Erosion"};

    // Y'all should be in init
    public Gui() {
        setMiglayout(new LC().wrapAfter(1), new AC().align("center"), new AC());

        ImageJComponent imageComponent = new ImageJComponent();

        final JMenuBar menuBar = new JMenuBar();
        final JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        Action openAction = new OpenAction(imageComponent ,"Open", "This is a file open menu option", KeyEvent.VK_O);
        final JMenuItem menuItemOpen = new JMenuItem(openAction);
        Action saveAction = new SaveAction(imageComponent, "Save", "This is a file save menu option", KeyEvent.VK_S);
        final JMenuItem menuItemSave = new JMenuItem(saveAction);
        fileMenu.add(menuItemOpen);
        fileMenu.add(menuItemSave);

        final JPanel footer = new JPanel();
        final JLabel label = new JLabel("Choose effect");
        final JComboBox<String> effects = new JComboBox<>(IMPLEMENTED_EFFECTS);
        effects.addItemListener(new ItemChangeListener(imageComponent));
        Action applyAction = new com.adasumizox.gui.actions.ApplyAction(imageComponent, "Apply", "This is button for saving effects", KeyEvent.VK_A);
        final JButton applyBtn = new JButton(applyAction);
        footer.add(label);
        footer.add(effects);
        footer.add(applyBtn);

        add(menuBar, "dock north");
        add(imageComponent, "center, width 100%, height 100%, wrap");
        add(footer, "dock south");

        pack();
    }
    public static void main(String[] args) throws InvocationTargetException, InterruptedException {
        invokeAndWait(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Gui();
                frame.setVisible(true);
                frame.setTitle("Image Manipulation");
            }
        });
    }
}