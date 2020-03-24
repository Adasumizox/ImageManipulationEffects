package com.adasumizox.gui.components;

import javax.swing.JFrame;

// http://www.miglayout.com/mavensite/apidocs/net/miginfocom/layout/ConstraintParser.html
// A constraint that holds the column or row constraints for the grid. It also holds the gaps between the rows and columns.
import net.miginfocom.layout.AC;
// Contains the constraints for an instance of the LC layout manager.
import net.miginfocom.layout.LC;
// A very flexible layout manager.
import net.miginfocom.swing.MigLayout;

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

    public void setMiglayout(LC layout, AC columns, AC rows) {
        setLayout(new MigLayout(layout, columns, rows));
    }

    public void setMiglayout() {
        setMiglayout(new LC(), new AC(), new AC());
    }
}
