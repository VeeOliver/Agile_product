package se.hkr.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController {

    @FXML
    Slider moodSlider;

    @FXML
    Slider tensionSlider;



    public void onLogoutBtnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcome-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("welcome.css").toExternalForm();
        //scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void onSubmitMT(ActionEvent event) throws IOException {
        double mood = moodSlider.getValue();
        double tension = tensionSlider.getValue();
        User user = User.getInstance("", "", "");
        Data.insertMood(mood,user);
        Data.insertTension(tension, user);

    }

}
