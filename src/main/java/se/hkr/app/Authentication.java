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

    Boolean checkLoginCredentials(String email, String password) throws SQLException {
        boolean value = false;
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

    void logError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Authentication error");
        alert.setHeaderText("Username or password incorrect");
        alert.setContentText("Did you register ?");
        alert.showAndWait();
    }

    // --- Registration methods ---

    Boolean[] checkAvailability(String personnummer, String email) throws SQLException {
        boolean emailAvailable = false;
        boolean personnummerAvailable = false;
        Connection con = DatabaseConnection.getInstance().connect();
        PreparedStatement stmt = con.prepareStatement(DatabaseApiSelect.getPersonnummer);
        stmt.setString(1, personnummer);

        ResultSet queryResult = stmt.executeQuery();
        if (queryResult.next()) {
            ;
        } else {
            personnummerAvailable = true;
        }

        stmt = con.prepareStatement(DatabaseApiSelect.getEmail);
        stmt.setString(1, email);

        queryResult = stmt.executeQuery();
        if (queryResult.next()) {
            ;
        } else {
            emailAvailable = true;
        }
        Boolean a[] = new Boolean[2];
        a[0] = emailAvailable;
        a[1] = personnummerAvailable;
        return a;
    }

    void registerUser(TextField registerPersonnummer, TextField registerName, TextField registerEmailField,
            PasswordField registerPasswordField) {
        Connection con = DatabaseConnection.getInstance().connect();
        DatabaseApiInsert.createUserEntry(con, registerPersonnummer.getText(), registerName.getText(),
                registerEmailField.getText(), registerPasswordField.getText());
    }

    void successRegistration() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Registration Successful!");
        alert.setHeaderText("Registration Successful!");
        alert.setContentText("You registered, now Log in.");
        alert.showAndWait();
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

    void resetRegField(TextField registerPersonnummer, TextField registerName, TextField registerEmailField,
            PasswordField registerPasswordField,
            PasswordField registerRepPasswordField) {
        registerPersonnummer.setText("");
        registerName.setText("");
        registerEmailField.setText("");
        registerPasswordField.setText("");
        registerRepPasswordField.setText("");
    }

    void resetLogFields(TextField emailField, PasswordField passwordField) {
        emailField.setText("");
        passwordField.setText("");
    }

    Boolean validEmail(String Email) {
        return Email.matches("\t^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
    }

    void emailFormatError(String email) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration error");
        alert.setHeaderText("Registration error");
        alert.setContentText("Invalid format for email");
        alert.showAndWait();
    }

    Boolean validPersonnummer(String personnummer) {
        return personnummer.matches("^(19|20)?[0-9]{6}[- ]?[0-9]{4}$");
    }

    void personFormatError(String personnummer) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Format error");
        alert.setHeaderText("Format error");
        alert.setContentText("Invalid format for personnummer");
        alert.showAndWait();
    }

    boolean validPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");

    }

    Boolean notEmptyFields(String personnummer, String name, String email, String password, String repPassword) {
        ArrayList<String> fields = new ArrayList<>(Arrays.asList(personnummer, name, email, password, repPassword));
        return fields.contains("");
    }

    void emptyFieldsError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Format error");
        alert.setHeaderText("Format error");
        alert.setContentText("You can't leave a field empty!");
        alert.showAndWait();
    }

    void switchToWelcome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcome-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass().getResource("welcome.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}