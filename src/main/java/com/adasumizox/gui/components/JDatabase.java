package com.adasumizox.gui.components;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JDatabase extends JComponent {
    private List<BufferedImage> _ImageList = new ArrayList<>();
    // Cause we don't want to give mutable list to user.
    private List<BufferedImage> ImageList = Collections.unmodifiableList(_ImageList);

    public List<BufferedImage> getImageList() {
        return ImageList;
    }
}
