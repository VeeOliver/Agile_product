package se.hkr.app;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLException;
import java.util.Objects;

public class WelcomeController {
    @FXML
    TextField emailField;

    @FXML
    PasswordField passwordField;

    @FXML
    Label factOfTheDay;


    Authentication auth = new Authentication();

    // Login Button
    public void onLoginBtnClick(ActionEvent event) throws IOException, SQLException {
        if (auth.checkLoginCredentials(emailField.getText(), passwordField.getText())) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu-view.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            String css = Objects.requireNonNull(this.getClass().getResource("tabs.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } else {
            auth.logError();
            auth.resetLogFields(emailField, passwordField);
        }
    }

    // Register Button
    public void onRegisterBtnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("register-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("welcome.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void onEnterKeyLogin() throws IOException, SQLException {
        System.out.printf("Enter key has been pressed");
    }

    public void onTabKeySwitchField() throws IOException {
        System.out.printf("TAB key has been pressed");

    }

    public void showFactOfTheDay(ActionEvent event) throws IOException, FileNotFoundException {
        File file = new File("facts.txt");
        final RandomAccessFile f = new RandomAccessFile(file, "r");
        final long randomLocation = (long) (Math.random() * (f.length() - 1));
        f.seek(randomLocation);
        f.readLine();
        String randomLine = f.readLine();
        f.close();
        String fact = randomLine;
        factOfTheDay.setText(fact);

    }
}


