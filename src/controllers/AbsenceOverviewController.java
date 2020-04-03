package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.user.Teacher;
import models.user.User;
import utils.FXUtils;

import java.time.LocalDate;

public class AbsenceOverviewController {

    @FXML private DatePicker datePicker;
    @FXML private TableView<?> lectureTable;
    @FXML private TableColumn<?, ?> subjectColumn;
    @FXML private TableColumn<?, ?> startTimeColumn;
    @FXML private TableColumn<?, ?> endTimeClolumn;
    @FXML private TableColumn<?, ?> studentsColumn;
    @FXML private PieChart absencePieChart;
    @FXML private Button doneButton;
    @FXML private Label loggedInUserLabel;

    private Teacher loggedInTeacher;

    @FXML
    void handleDatePick(ActionEvent event){}

    @FXML
    void handleDone(ActionEvent event){}

    private void setUpNodes() {
        this.loggedInUserLabel.setText(this.loggedInTeacher.toString());
        this.datePicker.setValue(LocalDate.now());
    }

    private void getLoggedInTeacher() throws IllegalAccessException {
        if (User.getLoggedInUser() instanceof Teacher)
            this.loggedInTeacher = (Teacher)User.getLoggedInUser();
        else
            throw new IllegalAccessException("Dit is de docentenkamer, je hebt geen toestemming om hier te zijn >:(");
    }

    private void pie() {
        // piechart data
        PieChart.Data[] data = new PieChart.Data[5];

        // string and integer data
        String[] status = {"Correct Answer", "Wrong Answer",
                "Compilation Error", "Runtime Error",
                "Others"};

        int[] values = {20, 30, 10, 4, 2};

        for (int i = 0; i < 5; i++) {
            data[i] = new PieChart.Data(status[i], values[i]);
        }

        // create a pie chart
        PieChart pie_chart = new
                PieChart(FXCollections.observableArrayList(data));

        // set line length of label
        pie_chart.setLabelLineLength(10.0f);

        // make labels visible
        pie_chart.setLabelsVisible(true);

        // set start angle
        pie_chart.setStartAngle(20.0f);

        // set anticlockwise
        pie_chart.setClockwise(false);

        // create a Group
        Group group = new Group(pie_chart);

        // create a scene
        Scene scene = new Scene(group, 500, 500);

        Stage stage = new Stage();

        // set the scene
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    private void initialize() {
        try {
            this.getLoggedInTeacher();
            this.setUpNodes();
            this.pie();
        } catch (IllegalAccessException exception) {
            FXUtils.showInfo(exception.getMessage());
            FXUtils.closeStage(this.loggedInUserLabel);
        } catch (Exception exception) {
            FXUtils.showError(exception);
        }
    }
}
