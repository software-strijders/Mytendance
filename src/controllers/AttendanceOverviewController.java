package controllers;

import enums.AttendanceType;
import enums.SubjectType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Attendance;
import models.Lecture;
import models.user.Teacher;
import models.user.User;
import utils.FXUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toCollection;

public class AttendanceOverviewController {

    @FXML private TitledPane attendancePane;
    @FXML private DatePicker datePicker;
    @FXML private TableView<Lecture> lectureTable;
    @FXML private TableColumn<Lecture, SubjectType> subjectColumn;
    @FXML private TableColumn<Lecture, LocalTime> startTimeColumn;
    @FXML private TableColumn<Lecture, LocalTime> endTimeColumn;
    @FXML private TableColumn<Lecture, String> classColumn;
    @FXML private TableColumn<Lecture, Integer> classSizeColumn;

    private Teacher teacher;
    private LocalDate selectedDate;
    private PieChart pieChart;

    @FXML
    private void initialize() {
        try {
            this.setUpUser();
            this.setUpDatePicker();
            this.setUpLectureTable();
        } catch (IllegalAccessException exception) {
            FXUtils.showInfo(exception.getMessage());
        } catch (Exception exception) {
            FXUtils.showError(exception);
        }
    }

    private void setUpUser() throws IllegalAccessException {
        if (User.getLoggedInUser() instanceof Teacher) {
            this.teacher = (Teacher)User.getLoggedInUser();
        } else
            throw new IllegalAccessException("Dit scherm is alleen toegankelijk voor docenten :(");
    }

    private void setUpDatePicker() {
        this.selectedDate = LocalDate.now();
        this.datePicker.setValue(this.selectedDate);
    }

    private void setUpLectureTable() {
        this.subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        this.startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        this.endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        this.classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        this.classSizeColumn.setCellValueFactory(new PropertyValueFactory<>("classSize"));
        this.lectureTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            try {
                this.updatePieChart();
            } catch (Exception exception) {
                FXUtils.showError(exception);
            }
        });
        // We do this last to trigger a this.updatePieChart() call
        this.updateLectureTable();
    }

    private void updateLectureTable() {
        this.lectureTable.setItems(FXCollections.observableList(
                this.teacher.getLecturesByDate(this.selectedDate)));
        // Indirectly triggers a call to this.updatePieChart()
        this.lectureTable.getSelectionModel().selectFirst();
    }

    private void setUpPieChart() {
        this.pieChart = new PieChart();
        this.pieChart.setLabelLineLength(8f);
        this.attendancePane.setContent(this.pieChart);
    }

    private void updatePieChart() {
        Lecture lecture = this.lectureTable.getSelectionModel().getSelectedItem();

        if (lecture == null)
            this.pieChart.getData().clear();
        else {
            this.setUpPieChart();
            // Get all attendances of the lecture object which was obtained from the currently selected row,
            // create a temporary dictionary by grouping all attendances by attendance type and their respective count,
            // sort the dictionary by attendance name and generate a list of pie chart slices for each dictionary item
            this.pieChart.setData(lecture.getAttendances().stream().collect(Collectors.groupingBy(Attendance::getType,
                    Collectors.counting())).entrySet().stream().sorted(Comparator.comparing(item -> item.getKey()
                    .toString())).map(item -> new PieChart.Data(String.format("%s: %.0f%%", item.getKey(),
                    item.getValue() * 100f / lecture.getAttendancesSize()), item.getValue()))
                    .collect(toCollection(FXCollections::observableArrayList)));
        }
    }

    @FXML
    void handleDatePick(ActionEvent event) {
        try {
            this.selectedDate = this.datePicker.getValue();
            this.updateLectureTable();
        } catch (Exception exception) {
            FXUtils.showError(exception);
        }
    }
}
