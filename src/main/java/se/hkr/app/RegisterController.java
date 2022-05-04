package se.hkr.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.lang.annotation.Target;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    TextField registerPersonnummer;

    @FXML
    TextField registerName;

    @FXML
    TextField registerEmailField;

    @FXML
    PasswordField registerPasswordField;

    @FXML
    PasswordField registerRepPasswordField;

    @FXML
    Button registerBtn;

    Authentication auth = new Authentication();

    // Register Button
    public void onRegisterBtnClick(ActionEvent event) throws IOException, SQLException {
        Boolean[] availability = auth.checkAvailability(registerPersonnummer.getText(), registerEmailField.getText());
        Boolean formatError = auth.checkFormatError(registerPersonnummer.getText(), registerName.getText(),
                registerEmailField.getText(), registerPasswordField.getText(), registerRepPasswordField.getText());
        Boolean isAvailable = availability[0] && availability[1];

        // Check format errors
        if (formatError) {
            auth.showFormatError(registerPersonnummer.getText(), registerName.getText(), registerEmailField.getText(),
                    registerPasswordField.getText(), registerRepPasswordField.getText());
        } else {
            // Check availability
            if (isAvailable) {
                auth.registerUser(registerPersonnummer.getText(), registerName.getText(), registerEmailField.getText(), registerPasswordField.getText());
                auth.successRegistration();
                auth.switchToWelcome(event);
            } else {
                auth.registerError();
            }
            auth.resetRegField(registerPersonnummer, registerName, registerEmailField, registerPasswordField,
                    registerRepPasswordField);
        }
    }

    // Back Button
    public void onBackBtnClick(ActionEvent event) throws IOException {
        auth.switchToWelcome(event);
    }

    // change field with tab
    public void onTabKeySwitchField(KeyEvent e) throws IOException {
        TextField t = (TextField) e.getSource();
        boolean correctKey = (e.getCode() == KeyCode.TAB);
        if (correctKey) {
            switch (t.getId()) {
                case "registerPersonnummer" -> registerName.requestFocus();
                case "registerName" -> registerEmailField.requestFocus();
                case "registerEmailField" -> registerPasswordField.requestFocus();
                case "registerPasswordField" -> registerRepPasswordField.requestFocus();
            }
        }
    }

    // Register with Enter key
    public void onEnterRegister(KeyEvent e){
        if (e.getCode() == KeyCode.ENTER){
            registerBtn.fire();
        }
    }

}

