package se.hkr.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;


public class RegisterController {

    @FXML
    TextField emailField;

    @FXML
    PasswordField passwordField;

    @FXML
    TextField registerEmailField;

    @FXML
    PasswordField registerPasswordField;

    @FXML
    PasswordField registerRepPasswordField;

    @FXML
    DatePicker registerBirthField;

    Authentication auth = new Authentication();

    // Register Button
    public void onRegisterBtnClick(ActionEvent event) throws IOException {
        if (registerPasswordField.getText().equals(registerRepPasswordField.getText())
                && !auth.checkAvailability(Data.users, registerEmailField.getText())) {
            auth.registerUser(registerEmailField, registerPasswordField);
            auth.successRegistration();
        } else if (auth.checkAvailability(Data.users, registerEmailField.getText())) {
            auth.registerError();
        } else {
            auth.registerPasswordError();
        }
        auth.resetRegField(registerEmailField, registerPasswordField, registerRepPasswordField, registerBirthField);
        auth.switchToWelcome(event);
    }

    // Back Button
    public void onBackBtnClick(ActionEvent event) throws IOException {
        auth.switchToWelcome(event);
    }

}
