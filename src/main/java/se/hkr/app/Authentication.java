package se.hkr.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Authentication {

    // --- Login methods ---

    public Boolean checkLoginCredentials(String email, String password) throws SQLException {
        Connection con = DatabaseConnection.getInstance().connect();
        PreparedStatement stmt = con.prepareStatement(DatabaseApiSelect.getLogin);
        stmt.setString(1, email);
        stmt.setString(2, password);

        ResultSet userData = stmt.executeQuery();
        if (!userData.next()) {
            return false;
        } else {
            String Personnummer = userData.getString(1);
            email = userData.getString(2);
            String Name = userData.getString(3);
            User user = User.getInstance(Personnummer, email, Name);
            user.setEmail(email);
            user.setName(Name);
            user.setPersonnummer(Personnummer);
            return true;

        }
    }

    public void logError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Authentication error");
        alert.setHeaderText("Username or password incorrect");
        alert.setContentText("Did you register ?");
        alert.showAndWait();
    }

    // --- Registration methods ---

    public Boolean[] checkAvailability(String personnummer, String email) throws SQLException {
        Boolean emailAvailable = false;
        Boolean personnummerAvailable = false;
        Connection con = DatabaseConnection.getInstance().connect();

        ResultSet queryResult = DatabaseApiSelect.checkPersonnummer(con, personnummer);
        if (!queryResult.next()) {
            personnummerAvailable = true;
        }
        queryResult = DatabaseApiSelect.checkEmail(con, email);
        if (!queryResult.next()) {
            emailAvailable = true;
        }

        Boolean arr[] = {emailAvailable, personnummerAvailable};
        return arr;
    }

    public void registerUser(TextField registerPersonnummer, TextField registerName, TextField registerEmailField,
                             PasswordField registerPasswordField) {
        Connection con = DatabaseConnection.getInstance().connect();
        DatabaseApiInsert.createUserEntry(con, registerPersonnummer.getText(), registerName.getText(),
                registerEmailField.getText(), registerPasswordField.getText());
    }

    public void successRegistration() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Registration Successful!");
        alert.setHeaderText("Registration Successful!");
        alert.setContentText("You registered, now Log in.");
        alert.showAndWait();
    }

    public void registerError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration error");
        alert.setHeaderText("Registration error");
        alert.setContentText("This account already exist");
        alert.showAndWait();
    }

    public void resetRegField(TextField registerPersonnummer, TextField registerName, TextField registerEmailField,
                              PasswordField registerPasswordField,
                              PasswordField registerRepPasswordField) {
        registerPersonnummer.setText("");
        registerName.setText("");
        registerEmailField.setText("");
        registerPasswordField.setText("");
        registerRepPasswordField.setText("");
    }

    public void resetLogFields(TextField emailField, PasswordField passwordField) {
        emailField.setText("");
        passwordField.setText("");
    }

    // Registration Fields RegEx

    public Boolean validEmail(String Email) {
        return Email.matches("^(.+)@(.+)$");
    }

    public Boolean validPersonnummer(String personnummer) {
        return personnummer.matches("^(19|20)?[0-9]{6}[- ]?[0-9]{4}$");
    }

    public Boolean validPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()-[{}]:;',?/*~$^+=<>]).{8,20}$");
    }

    public Boolean equalPassword(String pass1, String pass2) {
        return Objects.equals(pass1, pass2);
    }

    public boolean validName(String name) {
        return name.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$");
    }

    public Boolean checkFormatError(String personnummer, String name, String email, String password, String repPassword) {
        ArrayList<Boolean> fields = new ArrayList<>(Arrays.asList(
                validPersonnummer(personnummer),
                validName(name),
                validEmail(email),
                validPassword(password),
                equalPassword(password, repPassword)));

        return fields.contains(false);
    }

    public void showFormatError(String personnummer, String name, String email, String password, String repPassword) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration error");
        alert.setHeaderText("Registration error");

        ArrayList<Boolean> fields = new ArrayList<>(Arrays.asList(
                validPersonnummer(personnummer),
                validName(name),
                validEmail(email),
                validPassword(password),
                equalPassword(password, repPassword)));

        String falseField = "";

        if (!fields.get(0))
            falseField += "\nPersonnummer should be in format: DDMMYY-XXXX";
        if (!fields.get(1))
            falseField += "\nInvalid format for name";
        if (!fields.get(2))
            falseField += "\nEmail should be in format: user@domain.com";
        if (!fields.get(3))
            falseField += "\nInvalid format for password";
        if (!fields.get(4))
            falseField += "\nPassword not identical";

        alert.setContentText(falseField);
        alert.showAndWait();
    }

    public void switchToWelcome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcome-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("welcome.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}