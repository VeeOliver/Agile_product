package se.hkr.app;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.Color;
import javafx.stage.Stage;
import se.hkr.app.DatabaseApiSelect.RetrieveMode;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;

import org.knowm.xchart.*;
import org.knowm.xchart.CategorySeries.CategorySeriesRenderStyle;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler;

import javax.swing.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class MenuController {

    @FXML
    public void tensionChart() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    buildPieChart();
                } catch (SQLException | IOException e) {
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
    ChoiceBox<String> prompts;

    @FXML
    private AnchorPane chartArea;

    @FXML
    private TextArea journalEntryDisplay;

    @FXML
    private Button displayJournalButton;

    @FXML
    private DatePicker journalDate;

    @FXML
    private Button displayMTOneDay;

    @FXML
    private DatePicker dateMTOneday;

    @FXML
    private ImageView dailyMTGraph;

    public void onLogoutBtnClick(ActionEvent event) throws IOException {
        User.resetInstance();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("welcome-view.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("welcome.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    // Mood tab
    public void onSubmitMT(ActionEvent event) throws SQLException, IOException {
        double mood = moodSlider.getValue();
        double tension = tensionSlider.getValue();
        User user = User.getInstance();
        Data.insertMood(mood, user);
        Data.insertTension(tension, user);
        Data.submissionCompleteNote();

        
        //  LineChart test;
          //graph.getData().clear();
          
        //   XYChart.Series<String, Number> moodSeries = new XYChart.Series<String,
        //  Number>();
        //   moodSeries.getData().add(new XYChart.Data<String, Number>("January", 7));
        //   moodSeries.getData().add(new XYChart.Data<String, Number>("February", 6));
        //  moodSeries.getData().add(new XYChart.Data<String, Number>("March", 8));
        //   moodSeries.getData().add(new XYChart.Data<String, Number>("April", 8));
        //   moodSeries.getData().add(new XYChart.Data<String, Number>("May", 7));
        //   moodSeries.getData().add(new XYChart.Data<String, Number>("June", 5));
        //   moodSeries.getData().add(new XYChart.Data<String, Number>("July", 6));
        //   moodSeries.getData().add(new XYChart.Data<String, Number>("September", 4));
        //   moodSeries.getData().add(new XYChart.Data<String, Number>("October", 3));
        //   moodSeries.getData().add(new XYChart.Data<String, Number>("November", 7));
        //   moodSeries.getData().add(new XYChart.Data<String, Number>("December", 8));
        //  moodSeries.setName("Mood");
        //  graph.getData().add(moodSeries);
          
        //   XYChart.Series<String, Number> tensionSeries = new XYChart.Series<String,
        //   Number>();
        //   tensionSeries.getData().add(new XYChart.Data<String, Number>("January", 7));
        //   tensionSeries.getData().add(new XYChart.Data<String, Number>("February",
        //   10));
        //   tensionSeries.getData().add(new XYChart.Data<String, Number>("March", 10));
        //   tensionSeries.getData().add(new XYChart.Data<String, Number>("April", 8));
        //   tensionSeries.getData().add(new XYChart.Data<String, Number>("May", 6));
        //   tensionSeries.getData().add(new XYChart.Data<String, Number>("June", 2));
        //   tensionSeries.getData().add(new XYChart.Data<String, Number>("July", 3));
        //   tensionSeries.getData().add(new XYChart.Data<String, Number>("September",
        //   4));
        //  tensionSeries.getData().add(new XYChart.Data<String, Number>("October", 3));
        //  tensionSeries.getData().add(new XYChart.Data<String, Number>("November", 5));
        //   tensionSeries.getData().add(new XYChart.Data<String, Number>("December", 8));
        //   tensionSeries.setName("Tension");
        //   graph.getData().add(tensionSeries);
         

    }

    // Journal entry tab
    public void onSubmitJournalEntry(ActionEvent event) throws SQLException, IOException {
        String savedJournalEntry = journalEntry.getText().replaceAll("\n", System.getProperty("line.separator"));
        User user = User.getInstance("", "", "");
        Data.insertJournal(savedJournalEntry, user);
        Data.journalSubmittedNote();
        journalEntry.setText("");
        prompts.setValue("Select a prompt:");

    }

    public void initializeChoicebox(Event e){

        prompts.setValue("Select a prompt:");
        prompts.getItems().addAll( "How did you sleep last night?",
                "What was the high and the low of your day",
                "What are some things you are looking forward to?",
                "How are you feeling about your work/studies?",
                "What has been the biggest struggle of the past week?",
                "How would you describe yourself to a stranger?",
                "What is your best quality?",
                "What would you like to achieve in the near future?",
                "What has helped you the most during your difficult days?",
                "Write a message to your past self:");

    }

    // Chart tab
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

    private void buildPieChart() throws SQLException, IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.XChart).width(766).height(516).title("Day Scale")
                .build();
        // Colors
        Color DarkBlue = new Color(50, 168, 140, 171);
        Color lightGreen = new Color(0, 255, 0, 124);
        Color purple = new Color(239, 7, 239, 151);
        // Customize Chart
        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setToolTipsEnabled(false);
        Color[] colorsSeries = new Color[] { DarkBlue, purple, lightGreen, Color.CYAN };
        chart.getStyler().setSeriesColors(colorsSeries);

        // Series
        Connection con = DatabaseConnection.getInstance().connect();
        ArrayList<ArrayList> data = DatabaseApiSelect.getMTDataChart(con, User.getInstance().getPersonnummer());
        ArrayList<ArrayList> dataAvg = DatabaseApiSelect.getAvgMTDataChart(con, User.getInstance().getPersonnummer());
        ArrayList<Double> mood = data.get(0);
        ArrayList<Double> tension = data.get(1);
        ArrayList<Date> dates = data.get(2);
        ArrayList<Double> AvgMood = dataAvg.get(0);
        ArrayList<Double> AvgTension = dataAvg.get(1);
        ArrayList<Date> AvgDates = dataAvg.get(2);

        XYSeries tensionSeries = chart.addSeries("Tension", dates, tension);
        XYSeries moodSeries = chart.addSeries("Mood", dates, mood);
        XYSeries AvgtensionSeries = chart.addSeries("Average Tension", AvgDates, AvgTension);
        XYSeries AvgmoodSeries = chart.addSeries("Average Mood", AvgDates, AvgMood);

        tensionSeries.setXYSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        moodSeries.setXYSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        AvgtensionSeries.setXYSeriesRenderStyle(XYSeriesRenderStyle.Area);
        AvgmoodSeries.setXYSeriesRenderStyle(XYSeriesRenderStyle.Line);

        // Save chart to img
        // BitmapEncoder.saveBitmapWithDPI(chart,
        // "./src/main/resources/se/hkr/app/imgs/chart", BitmapEncoder.BitmapFormat.PNG,
        // 300);

        showChart(chart);
    }

    // Journal history tab
    public void onDisplayJournalButtonBtnClick(ActionEvent event) throws IOException, SQLException{
        LocalDate date = journalDate.getValue();
        String personnummer = User.getInstance().getPersonnummer();
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        String entries = JournalEntry.retrieveJournalEntry(con, date, personnummer);
        dbCon.disconnect();
        journalEntryDisplay.setText(entries);
    }

    // Daily chart tab -> Works only with updated database schema!
    // public void onDisplayMTOneDayButtonClick(ActionEvent event) {
    //     try {
    //         Connection con = DatabaseConnection.getInstance().connect();
    //         LocalDate date = dateMTOneday.getValue();
    //         String personnummer = User.getInstance().getPersonnummer();
    //         boolean noData = buildDayChart(con, date, personnummer);
    //         DatabaseConnection.getInstance().disconnect();
    //         String imageURL = noData
    //             ? "./src/main/resources/se/hkr/app/imgs/AchillesInforms.png"
    //             : "./src/main/resources/se/hkr/app/imgs/chartMTday.png";
    //         FileInputStream stream = new FileInputStream(imageURL);
    //         dailyMTGraph.setImage(new Image(stream));
    //     } catch (SQLException e) {
    //         System.out.println(e.getMessage());
    //     } catch (IOException e) {
    //         System.out.println(e.getMessage());
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //     }
    // }

    // private static boolean buildDayChart(Connection con, LocalDate date,
    //     String personnummer) throws SQLException, IOException {

    //     boolean noData = true;

    //     // Retrieve and parse data if there is data for the chosen date
    //     RetrieveMode mode = RetrieveMode.MOOD_TENSION;
    //     ArrayList<Data> mTList = DatabaseApiSelect.getData(con, mode, date,
    //         personnummer);
    //     if (!mTList.isEmpty()) {
    //         noData = false;
    //         ArrayList<String> daytimes = new ArrayList<>();
    //         ArrayList<Integer> moods = new ArrayList<>();
    //         ArrayList<Integer> tensions = new ArrayList<>();
    //         for (Data entryData : mTList) {
    //             MoodTension entry = (MoodTension) entryData;
    //             switch (entry.getDaytime()) {
    //                 case "1-Morning":
    //                     daytimes.add(entry.getDaytime());
    //                     break;
    //                 case "2-Noon":
    //                     daytimes.add(entry.getDaytime());
    //                     break;
    //                 case "3-Afternoon":
    //                     daytimes.add(entry.getDaytime());
    //                     break;
    //                 case "4-Evening":
    //                     daytimes.add(entry.getDaytime());
    //                     break;
    //                 default:
    //                     break;
    //             };
    //             moods.add(entry.getMood());
    //             tensions.add(entry.getTension());
    //         }

    //         // Create and customize Chart
    //         CategoryChart chart = new CategoryChartBuilder()
    //             .theme(Styler.ChartTheme.XChart).width(766)
    //             .height(516).title("Mood and tension over the day").build();

    //         Color DarkBlue = new Color(50, 168, 140, 171);
    //         Color lightGreen = new Color(0, 255, 0, 124);
    //         Color purple = new Color(239, 7, 239, 151);
    //         Color[] colorsSeries = new Color[] {DarkBlue, purple, lightGreen,
    //                 Color.CYAN};

    //         chart.getStyler().setSeriesColors(colorsSeries);
    //         chart.getStyler().setLegendVisible(true);
    //         chart.getStyler().setToolTipsEnabled(false);
    //         chart.getStyler().setStacked(true);
    //         chart.getStyler().setYAxisMax(10.0);
    //         chart.getStyler().setYAxisMin(0.0);

    //         CategorySeries tenSeries = chart.addSeries("Tension", daytimes, tensions);
    //         CategorySeries mooSeries = chart.addSeries("Mood", daytimes, moods);
    //         tenSeries.setChartCategorySeriesRenderStyle(CategorySeriesRenderStyle.Line);
    //         mooSeries.setChartCategorySeriesRenderStyle(CategorySeriesRenderStyle.Line);

    //         // Save chart to img
    //         BitmapEncoder.saveBitmapWithDPI(chart,
    //             "./src/main/resources/se/hkr/app/imgs/chartMTday",
    //             BitmapEncoder.BitmapFormat.PNG, 300);
    //     }
    //     return noData;
    // }
}
