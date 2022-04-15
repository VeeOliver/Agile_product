package se.hkr.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class WelcomeController {
    @ FXML
    TextField emailField;

    @ FXML
    PasswordField passwordField;

    @FXML
    TextField registerEmailField;

    @FXML
    PasswordField registerPasswordField;

    @FXML
    PasswordField registerRepPasswordField;

    @FXML
    DatePicker registerBirthField;


    public void onLoginBtnClick(ActionEvent event) throws IOException {
        if (checkLoginCredentials(emailField.getText(), passwordField.getText())) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("menu-view.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            String css = Objects.requireNonNull(this.getClass().getResource("tabs.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } else {
            logError();
            resetLogFields();
        }
    }


    public void onRegisterBtnClick() {
        if (registerPasswordField.getText().equals(registerRepPasswordField.getText()) && !checkAvailability(Data.users, registerEmailField.getText())) {
            registerUser();
            successRegistration();
        } else if (checkAvailability(Data.users, registerEmailField.getText())){
            registerError();
        } else {
            registerPasswordError();
        }
        resetRegField();
    }


    Boolean checkLoginCredentials (String email, String password) {
        boolean value = false;
        for (User user : Data.users) {
            value = user.email.equals(email) && user.password.equals(password);
            if (value) break;
        }
        return value;
    }


    void logError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Authentication error");
        alert.setHeaderText("Username or password incorrect");
        alert.setContentText("Did you register ?");
        alert.showAndWait();
    }


    void successRegistration() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Registration Successful!");
        alert.setHeaderText("Registration Successful!");
        alert.setContentText("You registered, now Log in.");
        alert.showAndWait();
    }


    Boolean checkAvailability(ArrayList<User> list, String email) {
        return list.stream().anyMatch(o -> o.email.equals(email));
    }


    void registerUser() {
        Data.users.add(new User(registerEmailField.getText(), registerPasswordField.getText()));
        Data.users.forEach(user -> System.out.println(user.email + " " + user.password));
    }


    void registerPasswordError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Password error");
        alert.setHeaderText("Password error");
        alert.setContentText("The two password are not identical");
        alert.showAndWait();
    }

    void registerError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration error");
        alert.setHeaderText("Registration error");
        alert.setContentText("This account already exist");
        alert.showAndWait();
    }


    void resetRegField() {
        registerEmailField.setText("");
        registerPasswordField.setText("");
        registerRepPasswordField.setText("");
        registerBirthField.setValue(null);
    }


    void resetLogFields() {
        emailField.setText("");
        passwordField.setText("");
    }

}

