package com.adasumizox.database;

import java.awt.image.BufferedImage;
import java.sql.Connection;

public interface imageDAO {
    public void selectImages(Connection c);
    public void selectImage(Connection c, int id);
    public void insertImage(Connection c, String description, String name,  String extension , BufferedImage image);
}
