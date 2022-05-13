package se.hkr.app;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    /**
     * TextField: Emal field.
     */
    @FXML
    private TextField emailField;

    /**
     * PasswordField: Password field.
     */
    @FXML
    private PasswordField passwordField;

    /**
     * Label: Fact of the day.
     */
    @FXML
    private Label factOfTheDay;

    /**
     * Button: Login button.
     */
    @FXML
    private Button loginBtn;

    /**
     * Button: About button.
     */
    @FXML
    private Button aboutBtn;

    /**
     * Button: Back button.
     */
    @FXML
    private Button backBtn;

    /**
     * Authentication object to access methods.
     */
    private Authentication auth = new Authentication();

    /**
     * Check login credentials and switch to Menu if login is successful.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    public void onLoginBtnClick(final ActionEvent event)
            throws IOException, SQLException {
        String email = emailField.getText();
        String password = passwordField.getText();
        if (auth.checkLoginCredentials(email, password)) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("menu-view.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene()
                .getWindow();
            Scene scene = new Scene(root);
            String css = Objects.requireNonNull(this.getClass()
                .getResource("tabs.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } else {
            Authentication.logError();
            auth.resetLogFields(emailField, passwordField);
        }
    }

    /**
     * Change to Register screen.
     * @param event
     * @throws IOException
     */
    public void onRegisterBtnClick(final ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
            .getResource("register-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene()
            .getWindow();
        Scene scene = new Scene(root);
        String css = Objects.requireNonNull(this.getClass()
            .getResource("welcome.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Enable enter key for Login button.
     * @param e
     * @throws IOException
     * @throws SQLException
     */
    public void onEnterKeyLogin(final KeyEvent e)
            throws IOException, SQLException {
        if (e.getCode() == KeyCode.ENTER) {
            loginBtn.fire();
        }
    }

    /**
     * Enable changing fields with tab key.
     * @param e
     * @throws IOException
     */
    public void onTabKeySwitchField(final KeyEvent e) throws IOException {
        if (e.getCode() == KeyCode.TAB) {
            passwordField.requestFocus();
        }
    }

    /**
     * Display a random funfact.
     * @param event
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void showFactOfTheDay(final ActionEvent event)
            throws IOException, FileNotFoundException {
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

    /**
     * Access About page.
     * @param event
     * @throws IOException
     */
    public void showAboutUs(final ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
            .getResource("about-us.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
