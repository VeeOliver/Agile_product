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
    /**
     * Test if given user exists in database and create user instance if so.
     * @param email
     * @param password
     * @return true if login credentials were correct |
     *         | false if user does not exist or password is wrong
     * @throws SQLException
     */
    public Boolean checkLoginCredentials(final String email,
            final String password) throws SQLException {
        Connection con = DatabaseConnection.getInstance().connect();
        String sql = """
                SELECT
                    personnummer, email, name
                FROM User
                WHERE email= ? and password = SHA1( ? );
                """;
        PreparedStatement stmt =
            con.prepareStatement(sql);
        stmt.setString(1, email);
        stmt.setString(2, password);

        ResultSet userData = stmt.executeQuery();
        if (!userData.next()) {
            return false;
        } else {
            String personnummer = userData.getString(1);
            String name = userData.getString(3);
            User user = User.getInstance(personnummer, email, name);
            user.setEmail(email);
            user.setName(name);
            user.setPersonnummer(personnummer);
            return true;
        }
    }

    /**
     * Display error message if login fails.
     */
    public static void logError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Authentication error");
        alert.setHeaderText("Username or password incorrect");
        alert.setContentText("Did you register?");
        alert.showAndWait();
    }

    // --- Registration methods ---

    /**
     * Check if email or personnummer are already used by another user.
     * @param personnummer
     * @param email
     * @return   [true, true] if email and personnummer are both available
     *         | [false, true] if email is used and personnummer is free
     *         | [true, false] if email is free and personnummer is used
     *         | [false, false] if both are used
     * @throws SQLException
     */
    public Boolean[] checkAvailability(final String personnummer,
            final String email) throws SQLException {
        Boolean emailAvailable = false;
        Boolean personnummerAvailable = false;
        Connection con = DatabaseConnection.getInstance().connect();

        ResultSet queryResult = DatabaseApiSelect
            .checkPersonnummer(con, personnummer);
        if (!queryResult.next()) {
            personnummerAvailable = true;
        }

        queryResult = DatabaseApiSelect.checkEmail(con, email);
        if (!queryResult.next()) {
            emailAvailable = true;
        }

        DatabaseConnection.getInstance().disconnect();

        Boolean[] arr = {emailAvailable, personnummerAvailable};
        return arr;
    }

    /**
     * Create a new user entry in remote database with entered data.
     * @param registerPersonnummer
     * @param registerName
     * @param registerEmailField
     * @param registerPasswordField
     * @throws SQLException
     */
    public void registerUser(final String registerPersonnummer,
            final String registerName, final String registerEmailField,
            final String registerPasswordField) throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        DatabaseApiInsert.createUserEntry(con, registerPersonnummer,
            registerName, registerEmailField, registerPasswordField);
        dbCon.disconnect();
    }

    /**
     * Display success message when registration succeedes.
     */
    public static void successRegistration() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Registration Successful!");
        alert.setHeaderText("Registration Successful!");
        alert.setContentText("You registered, now Log in.");
        alert.showAndWait();
    }

    /**
     * Display error message when a registration error occured.
     */
    public static void registerError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration error");
        alert.setHeaderText("Registration error");
        alert.setContentText("This account already exist");
        alert.showAndWait();
    }

    /**
     * Clear the registration fields from user text.
     * @param registerPersonnummer
     * @param registerName
     * @param registerEmailField
     * @param registerPasswordField
     * @param registerRepPasswordField
     */
    public void resetRegField(final TextField registerPersonnummer,
            final TextField registerName, final TextField registerEmailField,
            final PasswordField registerPasswordField,
            final PasswordField registerRepPasswordField) {
        registerPersonnummer.setText("");
        registerName.setText("");
        registerEmailField.setText("");
        registerPasswordField.setText("");
        registerRepPasswordField.setText("");
    }

    /**
     * Clear the login fields from user text.
     * @param emailField
     * @param passwordField
     */
    public void resetLogFields(final TextField emailField,
            final PasswordField passwordField) {
        emailField.setText("");
        passwordField.setText("");
    }

    // Registration Fields RegEx

    /**
     * Test if format of entered email-address is correct.
     * @param email
     * @return true if format is correct | false otherwise
     */
    public static Boolean validEmail(final String email) {
        return email.matches("^(.+)@(.+)$");
    }

    /**
     * Test if entered personnummer is in correct format.
     * @param personnummer
     * @return true if format is correct | false otehrwise
     */
    public static Boolean validPersonnummer(final String personnummer) {
        return personnummer.matches("^(19|20)?[0-9]{6}[- ]?[0-9]{4}$");
    }

    /**
     * Test if password contains necessary characters and is long enough.
     * @param password
     * @return true if password is valid | false otherwise
     */
    public static Boolean validPassword(final String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[!@#&()-[{}]:;',?/*~$^+=<>]).{8,20}$");
    }

    /**
     * Test if both entered passwords are equal.
     * @param pass1
     * @param pass2
     * @return true if passwords match | false otherwise
     */
    public static Boolean equalPassword(final String pass1,
            final String pass2) {
        return Objects.equals(pass1, pass2);
    }

    /**
     * Test if entered name contains only valid characters.
     * @param name
     * @return true if entered name contains only valid characters
     *         | false otherwise
     */
    public static Boolean validName(final String name) {
        return name.matches("^[a-zA-Z]{4,}(?: [a-zA-Z]+){0,2}$");
    }

    /**
     * Test if data entered for registration is valid.
     * @param personnummer
     * @param name
     * @param email
     * @param password
     * @param repPassword
     * @return true if all entered data is valid | false otherwise
     */
    public Boolean checkFormatError(final String personnummer,
            final String name, final String email,
            final String password, final String repPassword) {
        ArrayList<Boolean> fields = new ArrayList<>(Arrays.asList(
                validPersonnummer(personnummer),
                validName(name),
                validEmail(email),
                validPassword(password),
                equalPassword(password, repPassword)));
        return fields.contains(false);
    }

    /**
     * Display error message if entered data is invalid for registration.
     * @param personnummer
     * @param name
     * @param email
     * @param password
     * @param repPassword
     */
    public static void showFormatError(final String personnummer,
            final String name, final String email,
            final String password, final String repPassword) {
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

        if (!fields.get(0)) {
            falseField += "\nPersonnummer should be in format: DDMMYY-XXXX";
        }
        if (!fields.get(1)) {
            falseField += "\nInvalid format for name";
        }
        if (!fields.get(2)) {
            falseField += "\nEmail should be in format: user@domain.com";
        }
        if (!fields.get(3)) {
            falseField += "\nInvalid format for password";
        }
        if (!fields.get(4)) {
            falseField += "\nPassword not identical";
        }

        alert.setContentText(falseField);
        alert.showAndWait();
    }

    /**
     * Exit to the GUI welcome screen.
     * @param event
     * @throws IOException
     */
    public void switchToWelcome(final ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
            .getResource("welcome-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass()
            .getResource("welcome.css"))
            .toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}
