package se.hkr.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;

import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.awt.Color;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import org.knowm.xchart.*;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static se.hkr.app.DatabaseApiSelect.RetrieveMode.MOOD_TENSION;

public class MenuController {
    @FXML
    public void moodChart() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                buildXYChart();
            }
        });
    }



    @FXML
    public void tensionChart() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    buildPieChart();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @FXML
    Slider moodSlider;

    @FXML
    Slider tensionSlider;

    @FXML
    TextArea journalEntry;

    @FXML
    LineChart<String, Number> graph;

    @FXML
    private AnchorPane chartArea;


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

        /* LineChart test
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
        graph.getData().add(tensionSeries); */

    }


    public void onSubmitJournalEntry(ActionEvent event) throws IOException {
        String savedJournalEntry = journalEntry.getText().replaceAll("\n", System.getProperty("line.separator"));
        User user = User.getInstance("","","");
        Data.insertJournal(savedJournalEntry, user);
        Data.journalSubmittedNote();
        Data.clearOutJournalEntry(journalEntry);
        }
// Charts
    private void showChart(Chart chart) {
        JPanel chartPanel = new XChartPanel<>(chart);

        // for embedding swing in javafx
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(chartPanel);

        // for resizing plot to window
        AnchorPane.setLeftAnchor(swingNode, 0.0);
        AnchorPane.setRightAnchor(swingNode, 0.0);
        AnchorPane.setTopAnchor(swingNode, 0.0);
        AnchorPane.setBottomAnchor(swingNode, 0.0);

        // add chart to the chart area
        chartArea.getChildren().add(swingNode);

    }



    private List[] weeklyTension() throws SQLException {
        Connection con = DatabaseConnection.getInstance().connect();


        ArrayList<Data> mood;


        mood = DatabaseApiSelect.getData(con,MOOD_TENSION,getDate7DaysAgo(),getCurrentDate(),User.getInstance().getPersonnummer());
        System.out.println(getDate7DaysAgo());
        System.out.println(getCurrentDate());
        System.out.println(mood);
        for(Data data : mood){
            System.out.println(data.getDate());
        }



        return getTensionArray(mood);




    }

    private List<Double> getMoodArray(ArrayList<Data> data){

        List<Double> Mood = new ArrayList<>();

        for (Data entry : data) {
            MoodTension mood2 = (MoodTension) entry;
            Mood.add((double) mood2.getMood());
        }
        return Mood;
    }
    private List[] getTensionArray(ArrayList<Data> data){

        List<Double> Tension= new ArrayList<>();
        List<Date> date = new ArrayList<>();

        for (Data entry : data) {
            MoodTension tension2 = (MoodTension) entry;
            Tension.add((double) tension2.getTension());
            date.add(localDatetoDate(tension2.getDate()));
        }
        return new List[] { Tension, date };
    }

    private Date localDatetoDate(LocalDate local){
            //default time zone
            ZoneId defaultZoneId = ZoneId.systemDefault();

            //local date + atStartOfDay() + default time zone + toInstant() = Date
            Date date = Date.from(local.atStartOfDay(defaultZoneId).toInstant());

            return date;
    }



    private LocalDate getCurrentDate(){

        return convertToLocalDateViaMilisecond(new Date(System.currentTimeMillis()));

    };

    private LocalDate getDate7DaysAgo(){
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        return convertToLocalDateViaMilisecond(new Date(System.currentTimeMillis() - (50 * DAY_IN_MS)));
    }

    public LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    private void buildPieChart() throws SQLException {
        // Create Chart
        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.GGPlot2).width(766).height(516).title("Day Scale").build();
        Color DarkBlue = new Color(50, 82, 168);
        Color lightGreen = new Color(0, 255, 0, 40);
        // Customize Chart
        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setToolTipsEnabled(true);
        Color[] colorsSeries = new Color[]{DarkBlue, Color.CYAN, lightGreen};
        chart.getStyler().setSeriesColors(colorsSeries);

        // Series
        List<Date> xData = new ArrayList<>();

        List<Double> yData = new ArrayList<>();

        List<Double> AvgData = new ArrayList<>();

        List[] tension = weeklyTension();

        List<Double> zData = tension[0];
        List<Date> cData = tension[1];

        List<Date> wData = new ArrayList<>();

        for (Date entry : cData){
            System.out.println(entry.toString());
        }

        for(Double ten :zData){
            System.out.println(ten);
        }
        XYSeries tensionSeries = chart.addSeries("Tension", cData, zData);
        tensionSeries.setXYSeriesRenderStyle(XYSeriesRenderStyle.Scatter);



        showChart(chart);
    }

    private void buildXYChart() {
        double[] xData = new double[] { 0.0, 1.0, 2.0 };
        double[] yData = new double[] { 2.0, 1.0, 0.0 };

        // Create Chart
        XYChart chart = new XYChartBuilder().width(766).height(516).title("Day Scale").build();
        chart.addSeries("blah", xData, yData);

        showChart(chart);
    }

}
