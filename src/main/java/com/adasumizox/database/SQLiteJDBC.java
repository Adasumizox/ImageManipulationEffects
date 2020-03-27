package com.adasumizox.database;

import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteJDBC {
    private static final Logger LOGGER = Logger.getLogger(SQLiteJDBC.class.getName());
    public static void main(String[] args) {
        Connection c = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:test3.db");
            LOGGER.log(Level.FINE, "Opened database successfully");
            createTable(c, "Image");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            System.exit(0);
        }
        LOGGER.log(Level.FINE, "Table created successfully");
    }

    // Should check name of database
    public static void createTable(Connection c, String name) {
        Statement stmt = null;
        try{
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + name + " " +
                    "(id INT PRIMARY KEY               NOT NULL," +
                    " name                        TEXT NOT NULL," +
                    " description                 TEXT NOT NULL," +
                    " extension                   TEXT NOT NULL," +
                    " content                     BLOB NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            System.exit(0);
        }
    }

    // I am retarded
    // Should verify name of database
    // It is not most optimal way but it is compatible with jdbc
    // Eventually we can do query SELECT count(name) AS cnt_name FROM sqlite_master WHERE type='table' AND name='{table_name}';
    /*public static boolean checkIfTableExist(Connection c, String name) {
        name=name.toUpperCase();
        try (ResultSet res = c.getMetaData().getTables(null, null, name, null)) {
            while (res.next()) {
                String tName = res.getString("TABLE_NAME");
                if(tName != null && tName.equals(name)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            System.exit(0);
        }
        return false;
    }*/

    public static void selectImages(Connection c) {
        String sql = "SELECT ID, NAME, DESCRIPTION, EXTENSION, CONTENT FROM Images";

    }

    /*public void insertImage(Connection c, String description, String name,  String extension , BufferedImage image) {
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
    }*/

    /*public void selectImage() {
    }*/
}
