package agp.sanclemen.agp_proyecto.database;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PhotoInsertion {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:h2:C:/Users/angel/IdeaProjects/AGP_Proyecto/src/main/java/agp/sanclemen/agp_proyecto/database/agp_proyecto;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;AUTO_RECONNECT=TRUE;";
        String username = "";
        String password = "";

        String photoDirectory = "src/main/resources/Assets";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            File directory = new File(photoDirectory);

            if (directory.isDirectory()) {
                File[] files = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));

                if (files != null) {
                    for (File photoFile : files) {
                        try (FileInputStream fis = new FileInputStream(photoFile)) {
                            String nombreFoto = photoFile.getName().substring(0, photoFile.getName().length() - 4);
                            loadImages(connection, fis, Integer.parseInt(nombreFoto));
                        }
                    }
                }
            } else {
                System.out.println("Invalid directory: " + photoDirectory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadImages(Connection con, FileInputStream fis, int productId) {
        try {
            String insertSql = "INSERT INTO PRODUCT_PHOTO (PRODUCT_ID, PHOTO) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = con.prepareStatement(insertSql)) {
                preparedStatement.setInt(1, productId);
                preparedStatement.setBinaryStream(2, fis);
                // Execute the insert statement
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
