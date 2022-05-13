package se.hkr.app;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.sql.SQLException;


public class RegisterController {
    /**
     * Personnummer text field.
     */
    @FXML
    private TextField registerPersonnummer;

    /**
     * Name text field.
     */
    @FXML
    private TextField registerName;

    /**
     * Email text field.
     */
    @FXML
    private TextField registerEmailField;

    /**
     * Password text field.
     */
    @FXML
    private PasswordField registerPasswordField;

    /**
     * Password repetition text field.
     */
    @FXML
    private PasswordField registerRepPasswordField;

    /**
     * Register button.
     */
    @FXML
    private Button registerBtn;

    /**
     * Authentication object to access methods.
     */
    private Authentication auth = new Authentication();

    /**
     * Check input and register new User in database if valid.
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    public void onRegisterBtnClick(final ActionEvent event)
            throws IOException, SQLException {
        Boolean[] availability =
            auth.checkAvailability(registerPersonnummer.getText(),
            registerEmailField.getText());
        Boolean formatError =
            auth.checkFormatError(registerPersonnummer.getText(),
            registerName.getText(),
            registerEmailField.getText(), registerPasswordField.getText(),
            registerRepPasswordField.getText());
        Boolean isAvailable = availability[0] && availability[1];

        // Check format errors
        if (formatError) {
            Authentication.showFormatError(registerPersonnummer.getText(),
            registerName.getText(), registerEmailField.getText(),
            registerPasswordField.getText(),
            registerRepPasswordField.getText());
        } else {
            // Check availability
            if (isAvailable) {
                auth.registerUser(registerPersonnummer.getText(),
                    registerName.getText(), registerEmailField.getText(),
                    registerPasswordField.getText());
                Authentication.successRegistration();
                auth.switchToWelcome(event);
            } else {
                Authentication.registerError();
            }
            auth.resetRegField(registerPersonnummer, registerName,
                registerEmailField, registerPasswordField,
                registerRepPasswordField);
        }
    }

    /**
     * Change to Welcome screen when back button is clicked.
     * @param event
     * @throws IOException
     */
    public void onBackBtnClick(final ActionEvent event) throws IOException {
        auth.switchToWelcome(event);
    }

    /**
     * Enable switching fields with tab key.
     * @param e
     * @throws IOException
     */
    public void onTabKeySwitchField(final KeyEvent e) throws IOException {
        TextField t = (TextField) e.getSource();
        boolean correctKey = (e.getCode() == KeyCode.TAB);
        if (correctKey) {
            switch (t.getId()) {
                case "registerPersonnummer" -> registerName.requestFocus();
                case "registerName" -> registerEmailField.requestFocus();
                case "registerEmailField" -> registerPasswordField
                    .requestFocus();
                case "registerPasswordField" -> registerRepPasswordField
                    .requestFocus();
                default -> registerPersonnummer.requestFocus();
            }
        }
    }

    /**
     * Enable enter key in Register screen.
     * @param e
     */
    public void onEnterRegister(final KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            registerBtn.fire();
        }
    }
}
