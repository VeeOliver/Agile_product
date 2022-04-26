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
import java.util.Objects;

public class Authentication {

    ResultSet users = getUsers(DatabaseConnection.getInstance().connect(), "select * from user");

    // --- Hashing method ---

    String value = "this is a test";

	String sha1 = "";

    // --- Login methods ---

    Boolean checkLoginCredentials(String email, String password) throws SQLException {
        boolean value = false;
        while (users.next()) {
            String userEmail = users.getString("email");
            String userPassword = users.getString("password");
            value = userEmail.equals(email) && userPassword.equals(password);
            System.out.println(userEmail);
            System.out.println(userPassword);
            if (value) break;
        }
        return value;
    }

    ResultSet getUsers(Connection con, String sql) {
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            return stmt.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }

    ArrayList<String> getUserData(String dataName) {
        ArrayList<String> data = new ArrayList<>();
        try {
            while(users.next()) {
                String el = users.getString(dataName);
                data.add(el);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    void printUsers() {
        try {
            while(users.next()) {
                String personnummer = users.getString("personnummer");
                String name = users.getString("name");
                String email = users.getString("email");
                String password = users.getString("password");
                System.out.printf("Personnummer: %s, Name: %s, Email: %s, Password %s%n", personnummer, name, email, password);
            }
        } catch(SQLException e) {
            System.out.println(e);
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

    Boolean checkAvailability(ArrayList<String> list, String personnummer) {
        return list.stream().anyMatch(el -> el.equals(personnummer));
    }

    void registerUser(TextField registerPersonnummer, TextField registerName, TextField registerEmailField, PasswordField registerPasswordField) {
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

    void resetRegField(TextField registerPersonnummer, TextField registerName, TextField registerEmailField, PasswordField registerPasswordField,
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