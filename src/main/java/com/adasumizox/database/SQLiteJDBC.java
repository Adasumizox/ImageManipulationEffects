package com.adasumizox.database;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteJDBC {
    private static final Logger LOGGER = Logger.getLogger(SQLiteJDBC.class.getName());
    public static void main(String[] args) {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test7.db")){
            LOGGER.log(Level.FINE, "Opened database successfully");
            createImageTable(c);
            insertImage(c, "Temp", "Temp", ".jpeg", new BufferedImage(10,10, BufferedImage.TYPE_INT_ARGB));
            selectImages(c);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            System.exit(0);
        }
        LOGGER.log(Level.FINE, "Table created successfully");
    }

    // Should check name of database
    public static void createImageTable(Connection c) {
        String sql = "CREATE TABLE IF NOT EXISTS Image " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT     ," +
                " name                        TEXT NOT NULL," +
                " description                 TEXT NOT NULL," +
                " extension                   TEXT NOT NULL," +
                " content                     BLOB NOT NULL)";
        try (Statement stmt = c.createStatement()){
            stmt.execute(sql);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            System.exit(0);
        }
    }

    public static void selectImages(Connection c) {
        String sql = "SELECT id, name, description, extension, content " +
                     "FROM Image";
        try (Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                                   rs.getString("name") + "\t" +
                                   rs.getString("description") + "\t" +
                                   rs.getString("extension") + "\t" +
                                   rs.getBytes("content"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            System.exit(0);
        }
    }

    public static void selectImage(Connection c, int id) {
        String sql = "SELECT id, name, description, extension, content " +
                     "FROM Image " +
                     "WHERE id = ?";
        try (PreparedStatement pstmt = c.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("description") + "\t" +
                        rs.getString("extension") + "\t" +
                        rs.getBlob("content"));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            System.exit(0);
        }
    }

    public static void insertImage(Connection c, String description, String name,  String extension , BufferedImage image) {
        String sql = "INSERT INTO Image(description, name, extension, content) VALUES (?,?,?,?)";
        if (extension.equals(".jpg") || extension.equals(".jpeg") || extension.equals(".png")) {
            try (PreparedStatement pstmt = c.prepareStatement(sql)){
                pstmt.setString(1, description);
                pstmt.setString(2, name);
                pstmt.setString(3, extension);
                // Wow converting image to bytes wow such a good work.
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, extension.substring(1), baos);
                pstmt.setBytes(4, baos.toByteArray());
                pstmt.executeUpdate();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
                System.exit(0);
            }
        }
    }

    /*public void selectImage() {
    }*/
}
