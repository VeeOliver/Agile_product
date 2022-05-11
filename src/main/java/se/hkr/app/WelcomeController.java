package se.hkr.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    @FXML
    Button loginBtn;

    @FXML
    Button aboutBtn;

    @FXML
    Button backBtn;





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
            Authentication.logError();
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

    // login with Enter button
    public void onEnterKeyLogin(KeyEvent e) throws IOException, SQLException {
        if (e.getCode() == KeyCode.ENTER) {
            loginBtn.fire();
        }
    }

    //change fields with tab button
    public void onTabKeySwitchField(KeyEvent e) throws IOException {
        if (e.getCode() == KeyCode.TAB) {
            passwordField.requestFocus();
        }
    }

    // random fact generator
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

    // link to about us page
    public void showAboutUs(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("about-us.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}


