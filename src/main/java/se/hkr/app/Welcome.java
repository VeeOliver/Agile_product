package se.hkr.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Welcome extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Welcome.class.getResource("welcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = this.getClass().getResource("welcome.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Myrmidon");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        //launch();
        Connection con = null;
        try {
            String url = "jdbc:mariadb://127.0.0.1:5000/?user=root&password=root";
            con = DriverManager.getConnection(url);
            System.out.println("success");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}