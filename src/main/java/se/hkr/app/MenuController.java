package se.hkr.app;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Color;
import javafx.stage.Stage;
import se.hkr.app.DatabaseApiSelect.RetrieveMode;
import javafx.application.Platform;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.CategorySeries.CategorySeriesRenderStyle;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


public class MenuController {
    /**
     * Update weekly chart whenever Chart tab is entered.
     */
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
    /**
     * Slider: Mood slider.
     */
    @FXML
    private Slider moodSlider;
    /**
     * Slider: Tension slider.
     */
    @FXML
    private Slider tensionSlider;
    /**
     * TextArea: Journal entry submit.
     */
    @FXML
    private TextArea journalEntry;
    /**
     * ChoiceBox: Journaling prompts.
     */
    @FXML
    private ChoiceBox<String> prompts;
    /**
     * TextArea: Journal Entries.
     */
    @FXML
    private TextArea journalEntryDisplay;
    /**
     * Button: Journal Entries.
     */
    @FXML
    private Button displayJournalButton;
    /**
     * DatePicker: Journal entries.
     */
    @FXML
    private DatePicker journalDate;
    /**
     * Button: Daily mood-tension.
     */
    @FXML
    private Button displayMTOneDay;
    /**
     * DatePicker: Daily mood-tension.
     */
    @FXML
    private DatePicker dateMTOneday;
    /**
     * ImageView: Daily mood-tension chart.
     */
    @FXML
    private ImageView dailyMTGraph;
    /**
     * ImageView: Weekly mood-tension chart.
     */
    @FXML
    private ImageView weeklyChart;

    /**
     * Reset user and return to Welcome screen.
     * @param event
     */
    public void onLogoutBtnClick(final ActionEvent event) {
        try {
            User.resetInstance();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("welcome-view.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene()
                .getWindow();
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("welcome.css")
                .toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            configExceptionPopup();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            unknownExceptionPopup();
        }
    }

    // Mood tab
    /**
     * Take mood and tension from user and save in database.
     * @param event
     */
    public void onSubmitMT(final ActionEvent event) {
        try {
            double mood = moodSlider.getValue();
            double tension = tensionSlider.getValue();
            User user = User.getInstance();
            Data.insertMood(mood, user);
            Data.insertTension(tension, user);
            Data.submissionCompleteNote();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            sqlExceptionPopup();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            unknownExceptionPopup();
        } finally {
            if (DatabaseConnection.getInstance().getCon() != null) {
                try {
                    DatabaseConnection.getInstance().getCon().close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Journal entry tab
    /**
     * Take journal entry from user to database.
     * @param event
     */
    public void onSubmitJournalEntry(final ActionEvent event) {
        try {
            String savedJournalEntry = journalEntry.getText().replaceAll("\n",
                System.getProperty("line.separator"));
            User user = User.getInstance("", "", "");
            Data.insertJournal(savedJournalEntry, user);
            Data.journalSubmittedNote();
            journalEntry.setText("");
            prompts.setValue("Select a prompt:");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            sqlExceptionPopup();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            unknownExceptionPopup();
        } finally {
            if (DatabaseConnection.getInstance().getCon() != null) {
                    try {
                        DatabaseConnection.getInstance().disconnect();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                        sqlExceptionPopup();
                    }
            }
        }
    }

    /**
     * Define prompts for dropdown menu.
     * @param e
     */
    public void initializeChoicebox(final Event e) {
        prompts.setValue("Select a prompt:");
        prompts.getItems().addAll("How did you sleep last night?",
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
    /**
     * Create weekly mood-tension chart.
     * @throws SQLException
     * @throws IOException
     */
    private void buildPieChart() throws SQLException, IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().theme(Styler.ChartTheme.Matlab)
            .width(766).height(516).title("Day Scale").build();
        // Colors
        Color darkBlue = new Color(50, 168, 140, 171);
        Color lightGreen = new Color(0, 255, 0, 124);
        Color purple = new Color(239, 7, 239, 151);
        // Customize Chart
        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setToolTipsEnabled(false);
        Color[] colorsSeries = new Color[] {darkBlue, purple, lightGreen,
                Color.CYAN};
        chart.getStyler().setSeriesColors(colorsSeries);
        chart.getStyler().setYAxisMax(10.0);
        chart.getStyler().setYAxisMin(0.0);

        // Series
        Connection con = DatabaseConnection.getInstance().connect();
        ArrayList<ArrayList> data = DatabaseApiSelect.getMTDataChart(con,
            User.getInstance().getPersonnummer());
        ArrayList<ArrayList> dataAvg = DatabaseApiSelect.getAvgMTDataChart(con,
            User.getInstance().getPersonnummer());
        ArrayList<Double> mood = data.get(0);
        ArrayList<Double> tension = data.get(1);
        ArrayList<Date> dates = data.get(2);
        ArrayList<Double> avgMood = dataAvg.get(0);
        ArrayList<Double> avgTension = dataAvg.get(1);
        ArrayList<Date> avgDates = dataAvg.get(2);

        XYSeries tensionSeries = chart.addSeries("Tension", dates, tension);
        XYSeries moodSeries = chart.addSeries("Mood", dates, mood);
        XYSeries avgTensionSeries = chart.addSeries("Average Tension",
            avgDates, avgTension);
        XYSeries avgMoodSeries = chart.addSeries("Average Mood", avgDates,
            avgMood);

        tensionSeries.setXYSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        moodSeries.setXYSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        avgTensionSeries.setXYSeriesRenderStyle(XYSeriesRenderStyle.Area);
        avgMoodSeries.setXYSeriesRenderStyle(XYSeriesRenderStyle.Line);

        BitmapEncoder.saveBitmapWithDPI(chart,
        "./src/main/resources/se/hkr/app/imgs/chart",
            BitmapEncoder.BitmapFormat.PNG, 300);

        String imageURL = "./src/main/resources/se/hkr/app/imgs/chart.png";
        try (FileInputStream stream = new FileInputStream(imageURL)) {
            weeklyChart.setImage(new Image(stream));
       }
    }

    // Journal history tab
    /**
     * Display journal entries for chosen day.
     * @param event
     * @throws SQLException
     */
    public void onDisplayJournalButtonBtnClick(final ActionEvent event)
            throws SQLException {
        try {
            Connection con = DatabaseConnection.getInstance().connect();
            LocalDate date = journalDate.getValue();
            String personnummer = User.getInstance().getPersonnummer();
            String entries = JournalEntry.retrieveJournalEntry(con, date,
                personnummer);
            DatabaseConnection.getInstance().disconnect();
            journalEntryDisplay.setText(entries);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            sqlExceptionPopup();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            unknownExceptionPopup();
        } finally {
            if (DatabaseConnection.getInstance().getCon() != null) {
                DatabaseConnection.getInstance().disconnect();
            }
        }
    }

    // Daily chart tab
    /**
     * Display daily mood-tension chart or placeholder picture when no data.
     * @param event
     */
    public void onDisplayMTOneDayButtonClick(final ActionEvent event) {
        try {
            Connection con = DatabaseConnection.getInstance().connect();
            LocalDate date = dateMTOneday.getValue();
            String personnummer = User.getInstance().getPersonnummer();
            boolean noData = buildDayChart(con, date, personnummer);
            DatabaseConnection.getInstance().disconnect();
            String imageURL = noData
                ? "./src/main/resources/se/hkr/app/imgs/AchillesInforms.png"
                : "./src/main/resources/se/hkr/app/imgs/chartMTday.png";
            try (FileInputStream stream = new FileInputStream(imageURL)) {
                dailyMTGraph.setImage(new Image(stream));
            } catch (IOException e) {
                System.out.println(e.getMessage());
                ioExceptionPopup(imageURL);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            sqlExceptionPopup();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (DatabaseConnection.getInstance().getCon() != null) {
                    DatabaseConnection.getInstance().disconnect();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                sqlExceptionPopup();
            }
        }
    }

    /**
     * Create a mood-tension chart for given day.
     * @param con
     * @param date
     * @param personnummer
     * @return true when data for chart exists | false otherwise
     * @throws SQLException
     */
    private static boolean buildDayChart(final Connection con,
        final LocalDate date, final String personnummer) throws SQLException {

        boolean noData = true;

        // Retrieve and parse data if there is data for the chosen date
        RetrieveMode mode = RetrieveMode.MOOD_TENSION;
        ArrayList<Data> mTList = DatabaseApiSelect.getData(con, mode, date,
            personnummer);
        if (!mTList.isEmpty()) {
            noData = false;
            ArrayList<String> daytimes = new ArrayList<>();
            ArrayList<Integer> moods = new ArrayList<>();
            ArrayList<Integer> tensions = new ArrayList<>();
            for (Data entryData : mTList) {
                MoodTension entry = (MoodTension) entryData;
                switch (entry.getDaytime()) {
                    case "1-Morning":
                        daytimes.add(entry.getDaytime());
                        break;
                    case "2-Noon":
                        daytimes.add(entry.getDaytime());
                        break;
                    case "3-Afternoon":
                        daytimes.add(entry.getDaytime());
                        break;
                    case "4-Evening":
                        daytimes.add(entry.getDaytime());
                        break;
                    default:
                        break;
                }
                moods.add(entry.getMood());
                tensions.add(entry.getTension());
            }

            // Create and customize Chart
            CategoryChart chart = new CategoryChartBuilder()
                .theme(Styler.ChartTheme.XChart).width(766)
                .height(516).title("Mood and tension over the day").build();

            Color darkBlue = new Color(50, 168, 140, 171);
            Color lightGreen = new Color(0, 255, 0, 124);
            Color purple = new Color(239, 7, 239, 151);
            Color[] colorsSeries = new Color[] {darkBlue, purple, lightGreen,
                    Color.CYAN};

            chart.getStyler().setSeriesColors(colorsSeries);
            chart.getStyler().setLegendVisible(true);
            chart.getStyler().setToolTipsEnabled(false);
            chart.getStyler().setStacked(true);
            chart.getStyler().setYAxisMax(10.0);
            chart.getStyler().setYAxisMin(0.0);

            CategorySeries tenSeries = chart.addSeries("Tension", daytimes,
                tensions);
            CategorySeries mooSeries = chart.addSeries("Mood", daytimes,
                moods);
            tenSeries.setChartCategorySeriesRenderStyle(
                    CategorySeriesRenderStyle.Line);
            mooSeries.setChartCategorySeriesRenderStyle(
                    CategorySeriesRenderStyle.Line);

            // Save chart to img
            try {
                BitmapEncoder.saveBitmapWithDPI(chart,
                    "./src/main/resources/se/hkr/app/imgs/chartMTday",
                    BitmapEncoder.BitmapFormat.PNG, 300);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                chartBuildExceptionPopup();
            }
        }
        return noData;
    }

    //Alerts for error handling
    /**
     * Display error message when SQl exception is thrown.
     */
    public static void sqlExceptionPopup() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error when connecting to database");
        alert.setContentText("Please check your internet connection and try"
            + "gain later\nPlease check the error message in the terminal for"
            + " more information");
        alert.showAndWait();
    }

    /**
     * Display error message when IO exception is thrown.
     * @param url
     */
    public static void ioExceptionPopup(final String url) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error when opening a resource");
        alert.setContentText("Resource at " + url + " not found"
            + "\nPlease check the error message in the terminal"
            + " for more information");
        alert.showAndWait();
    }

    /**
     * Display error message when configuration exception is thrown.
     */
    public static void configExceptionPopup() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error in the confguration file");
        alert.setContentText("Please check the error message in the terminal"
            + " for more information");
        alert.showAndWait();
    }

    /**
     * Display error message when unknown exception is thrown.
     */
    public static void unknownExceptionPopup() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An unknown error occurred");
        alert.setContentText("Please check the error message in the terminal"
            + " for more information");
        alert.showAndWait();
    }

    /**
     * Display error message when chart creation fails.
     */
    public static void chartBuildExceptionPopup() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error when creating the chart");
        alert.setContentText("Please check the error message in the terminal"
            + " for more information");
        alert.showAndWait();
    }
}
