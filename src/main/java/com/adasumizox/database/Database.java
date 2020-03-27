package com.adasumizox.database;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Singleton boyz
public class Database implements imageDAO {
    private static Database instance = new Database();
    private static final Logger LOGGER = Logger.getLogger(Database.class.getName());
    private static Connection c;

    // TODO: Add thumbnails for photo and make component for showing them / Loading images
    // TODO: Add component for saving photo and decide if instert it or update it based on name or id idk

    public Connection getConnection() {
        return c;
    }


    private Database() {
        c = databaseInit();
        createImageTable(c);
    }

    public static Database getInstance() {
        return instance;
    }

    private Connection databaseInit() {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test7.db")) {
            LOGGER.log(Level.FINE, "Opened database successfully");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            System.exit(0);
        }
        return c;
    }

    private void createImageTable(Connection c) {
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

    public void selectImages(Connection c) {
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

    public void selectImage(Connection c, int id) {
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

    public void insertImage(Connection c, String description, String name,  String extension , BufferedImage image) {
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
}
