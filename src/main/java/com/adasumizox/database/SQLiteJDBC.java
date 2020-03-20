package com.adasumizox.database;

import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteJDBC {
    private static final Logger LOGGER = Logger.getLogger(SQLiteJDBC.class.getName());
    public static void main(String args[]) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            LOGGER.log(Level.FINE, "Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IMAGE " +
                            "(ID INT PRIMARY KEY AUTOINCREMENT NOT NULL," +
                            " NAME                        TEXT NOT NULL," +
                            " DESCRIPTION                 TEXT NOT NULL," +
                            " EXTENSION                   TEXT NOT NULL," +
                            " CONTENT                     BLOB NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            System.exit(0);
        }
        LOGGER.log(Level.FINE, "Table created successfully");
    }

    public void insertImage(Connection c, String description, String name,  String extension , BufferedImage image) {
        Statement stmt = null;
        if (extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png")) {
            try {
                stmt = c.createStatement();
                String sql = "INSERT INTO IMAGE (NAME, DESCRIPTION, EXTENSION, CONTENT)" +
                             "VALUES ('" + name + "', '" + description + "', '" + extension + "', " + image.toString() + ")";
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
                System.exit(0);
            }
        }
    }

    public void selectImage() {

    }
}
