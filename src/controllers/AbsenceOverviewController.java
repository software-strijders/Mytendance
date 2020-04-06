package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AbsenceOverviewController {

    @FXML private DatePicker datePicker;
    @FXML private TableView<?> lectureTable;
    @FXML private TableColumn<?, ?> subjectColumn;
    @FXML private TableColumn<?, ?> startTimeColumn;
    @FXML private TableColumn<?, ?> endTimeClolumn;
    @FXML private TableColumn<?, ?> studentsColumn;
    @FXML private PieChart absencePieChart;
    @FXML private Button doneButton;
    @FXML private Label loggedInLabel;

    @FXML
    void handleDatePick(ActionEvent event){}

    @FXML
    void handleDone(ActionEvent event){}
}
