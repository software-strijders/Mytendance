package controllers;

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
import java.util.ArrayList;
import java.util.List;

public class StudentLectureOverviewController {
    private Student student;
    private LocalDate selectedDate;

    @FXML
    private DatePicker datePicker;
    @FXML
    private AnchorPane datePickerAnchorPane;
    @FXML
    private TableView<Lecture> lectureTable;
    @FXML
    private TableColumn<?, ?> subjectColumn;
    @FXML
    private TableColumn<?, ?> startTimeColumn;
    @FXML
    private TableColumn<?, ?> endTimeColumn;
    @FXML
    private Button doneButton;
    @FXML
    private Label studentLabel;

    @FXML
    private void initialize() {
        datePicker.setManaged(false);
        DatePickerSkin skin = new DatePickerSkin(datePicker);
        datePickerAnchorPane.getChildren().add(skin.getPopupContent());
        try {
            this.setUpUser();
            this.setUpDatePicker();
            this.setUpLectureTable();
        } catch (IllegalAccessException exception) {
            FXUtils.showInfo(exception.getMessage());
            FXUtils.closeStage(this.studentLabel);
        } catch (Exception exception) {
            FXUtils.showError(exception);
        }
    }

    private void setUpUser() throws IllegalAccessException {
        if (User.getLoggedInUser() instanceof Student) {
            this.student = (Student) User.getLoggedInUser();
            this.studentLabel.setText(this.student.toString());
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
        this.updateLectureTable();
    }

    private void updateLectureTable() {
        List<Lecture> lectures = new ArrayList<Lecture>();
        for (Class theClass : this.student.getClasses()) {
            lectures.addAll(theClass.getLecturesByDate(this.datePicker.getValue()));
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
