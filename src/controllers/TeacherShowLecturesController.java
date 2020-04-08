package controllers;


import enums.SubjectType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.VBox;
import models.Lecture;
import models.user.Teacher;
import models.user.User;
import utils.FXUtils;
import java.time.LocalDate;
import java.time.LocalTime;


public class TeacherShowLecturesController {

    @FXML private VBox vbox;
    @FXML private TableView<Lecture> lectureTable;
    @FXML private TableColumn<Lecture, SubjectType> subjectColumn;
    @FXML private TableColumn<Lecture, LocalTime> startTimeColumn;
    @FXML private TableColumn<Lecture, LocalTime> endTimeColumn;
    @FXML private TableColumn<Lecture, String> classColumn;
    @FXML private TableColumn<Lecture, Integer> classSizeColumn;

    private Teacher teacher;
    private LocalDate selectedDate;
    private DatePicker datePicker = new DatePicker();

    @FXML
    private void initialize() {
        datePicker.getStyleClass().add("hide--icon");
        datePicker.setManaged(false);
        DatePickerSkin skin = new DatePickerSkin(datePicker);
        vbox.getChildren().add(skin.getPopupContent());
        datePicker.setOnAction(this::handleDatePick);

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
        this.updateLectureTable();
    }

    private void updateLectureTable() {
        this.lectureTable.setItems(FXCollections.observableList(
                this.teacher.getLecturesByDate(this.selectedDate)));
        this.lectureTable.getSelectionModel().selectFirst();
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

    @FXML
    void handleDone(ActionEvent event) {
        FXUtils.loadView("hoofdmenu", "/views/TeacherMenu.fxml", event);
    }
}
