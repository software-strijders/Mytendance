package controllers;

import enums.SubjectType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;
import models.Lecture;
import models.user.Student;
import models.user.User;
import models.Class;
import utils.FXUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StudentLectureOverviewController {
    private Student student;
    private LocalDate selectedDate;

    @FXML private DatePicker datePicker;
    @FXML private AnchorPane datePickerAnchorPane;
    @FXML private TableView<Lecture> lectureTable;
    @FXML private TableColumn<Lecture, String> subjectColumn;
    @FXML private TableColumn<LocalDateTime, LocalTime> startTimeColumn;
    @FXML private TableColumn<LocalDateTime, LocalTime> endTimeColumn;

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
        if (User.getLoggedInUser() instanceof Student) {
            this.student = (Student) User.getLoggedInUser();
        } else
            throw new IllegalAccessException("Dit scherm is alleen toegankelijk voor studenten :(");
    }

    private void setUpDatePicker() {
        datePicker.setManaged(false);
        DatePickerSkin skin = new DatePickerSkin(datePicker);
        datePickerAnchorPane.getChildren().add(skin.getPopupContent());
        this.selectedDate = LocalDate.now();
        this.datePicker.setValue(this.selectedDate);
    }

    private void setUpLectureTable() {
        this.subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        this.startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        this.endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        this.updateLectureTable();
    }

    private void updateLectureTable() {
        List<Lecture> lectures = new ArrayList<Lecture>();
        for (Class theClass : this.student.getClasses()) {
            lectures.addAll(theClass.getLecturesByDate(this.selectedDate));
        }
        this.lectureTable.setItems(FXCollections.observableList(lectures));
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
        FXUtils.loadView("Hoofdmenu", "/views/StudentMenu.fxml", event);
    }
}
