package se.hkr.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class Welcome extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Welcome.class.getResource("welcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = this.getClass().getResource("welcome.css").toExternalForm();
        scene.getStylesheets().add(css);
        Image appIcon = new Image("File:tinyicon.png");
        stage.getIcons().add(appIcon);
        stage.setTitle("Myrmidon");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}