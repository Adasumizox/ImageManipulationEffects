package com.adasumizox.gui.components;

import javax.swing.JFrame;

public abstract class GuiFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public GuiFrame() {
        setTitle(getClass().getCanonicalName());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public GuiFrame(String name) {
        this();
        setName(name);
    }
}
