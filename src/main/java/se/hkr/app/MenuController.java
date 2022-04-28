package se.hkr.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MenuController {

    @FXML
    Slider moodSlider;

    @FXML
    Slider tensionSlider;

    @FXML
    TextArea journalEntry;

    @FXML
    LineChart<String, Number> graph;



    public void onLogoutBtnClick(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcome-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("welcome.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void onSubmitMT(ActionEvent event) throws IOException {
        double mood = moodSlider.getValue();
        double tension = tensionSlider.getValue();
        User user = User.getInstance();
        Data.insertMood(mood,user);
        Data.insertTension(tension, user);
        Data.submissionCompleteNote();

        // LineChart test
        graph.getData().clear();

        XYChart.Series<String, Number> moodSeries = new XYChart.Series<String, Number>();
        moodSeries.getData().add(new XYChart.Data<String, Number>("January", 7));
        moodSeries.getData().add(new XYChart.Data<String, Number>("February", 6));
        moodSeries.getData().add(new XYChart.Data<String, Number>("March", 8));
        moodSeries.getData().add(new XYChart.Data<String, Number>("April", 8));
        moodSeries.getData().add(new XYChart.Data<String, Number>("May", 7));
        moodSeries.getData().add(new XYChart.Data<String, Number>("June", 5));
        moodSeries.getData().add(new XYChart.Data<String, Number>("July", 6));
        moodSeries.getData().add(new XYChart.Data<String, Number>("September", 4));
        moodSeries.getData().add(new XYChart.Data<String, Number>("October", 3));
        moodSeries.getData().add(new XYChart.Data<String, Number>("November", 7));
        moodSeries.getData().add(new XYChart.Data<String, Number>("December", 8));
        moodSeries.setName("Mood");
        graph.getData().add(moodSeries);

        XYChart.Series<String, Number> tensionSeries = new XYChart.Series<String, Number>();
        tensionSeries.getData().add(new XYChart.Data<String, Number>("January", 7));
        tensionSeries.getData().add(new XYChart.Data<String, Number>("February", 10));
        tensionSeries.getData().add(new XYChart.Data<String, Number>("March", 10));
        tensionSeries.getData().add(new XYChart.Data<String, Number>("April", 8));
        tensionSeries.getData().add(new XYChart.Data<String, Number>("May", 6));
        tensionSeries.getData().add(new XYChart.Data<String, Number>("June", 2));
        tensionSeries.getData().add(new XYChart.Data<String, Number>("July", 3));
        tensionSeries.getData().add(new XYChart.Data<String, Number>("September", 4));
        tensionSeries.getData().add(new XYChart.Data<String, Number>("October", 3));
        tensionSeries.getData().add(new XYChart.Data<String, Number>("November", 5));
        tensionSeries.getData().add(new XYChart.Data<String, Number>("December", 8));
        tensionSeries.setName("Tension");
        graph.getData().add(tensionSeries);
    }


    public void onSubmitJournalEntry(ActionEvent event) throws IOException {
        String savedJournalEntry = journalEntry.getText().replaceAll("\n", System.getProperty("line.separator"));
        User user = User.getInstance("","","");
        Data.insertJournal(savedJournalEntry, user);
        Data.journalSubmittedNote();
        Data.clearOutJournalEntry(journalEntry);
        }
}
