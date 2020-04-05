package controllers;

import enums.AttendanceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Attendance;
import models.Class;
import models.Lecture;
import models.user.Teacher;
import models.user.User;
import utils.FXUtils;
import java.time.LocalDate;

public class TakeAttendanceController {

    @FXML private Label teacherLabel;
    @FXML private ListView<Attendance> absentListView;
    @FXML private ListView<Attendance> presentListView;
    @FXML private ListView<Lecture> lectureListView;
    @FXML private ComboBox<Class> classComboBox;
    @FXML private DatePicker attendanceDatePicker;

    private Teacher teacher;

    @FXML
    private void initialize() throws IllegalAccessException {
        this.setUpUser();
        this.fillClassComboBox();
        this.attendanceDatePicker.setValue(LocalDate.now());
    }

    private void setUpUser() throws IllegalAccessException {
        if (User.getLoggedInUser() instanceof Teacher) {
            this.teacher = (Teacher) User.getLoggedInUser();
            this.teacherLabel.setText(this.teacher.toString());
        } else
            throw new IllegalAccessException("Dit scherm is alleen toegankelijk voor docenten :(");
    }

    @FXML
    private void fillClassComboBox() {
        this.classComboBox.setItems(FXCollections.observableArrayList(this.teacher.getAllClasses()));
    }

    @FXML
    public void onClassComboBoxClick() {
        LocalDate selectedDate = this.attendanceDatePicker.getValue();
        Class selectedClass = this.classComboBox.getValue();

        ObservableList<Lecture> allLecturesFromSelectedDate = FXCollections.observableArrayList();

        for (Lecture lecture : selectedClass.getLectures()) {
            if (lecture.getStartDate().toLocalDate().isEqual(selectedDate)) {
                allLecturesFromSelectedDate.add(lecture);
            }
        }
        this.lectureListView.setItems(allLecturesFromSelectedDate);
    }

    @FXML
    public void onLectureListViewClick() {
        try {
            ObservableList<Attendance> absentStudents = FXCollections.observableArrayList();
            ObservableList<Attendance> presentStudents = FXCollections.observableArrayList();
            Lecture selectedLecture = this.lectureListView.getSelectionModel().getSelectedItem();

            for (Attendance attendance : selectedLecture.getAttendances())
                if (attendance.getType().equals(AttendanceType.PRESENT))
                    presentStudents.add(attendance);
                else
                    absentStudents.add(attendance);

            this.absentListView.setItems(absentStudents);
            this.presentListView.setItems(presentStudents);
        } catch (NullPointerException exception) {
            FXUtils.showInfo("Er is geen les geselecteerd :(");
        }
    }

    @FXML
    public void onShowReasonClick(ActionEvent event) {
        try {
            Attendance selectedAbsent = this.absentListView.getSelectionModel().getSelectedItem();
            FXUtils.showInfo("Reden van absentie", selectedAbsent.getDescription());
        } catch (NullPointerException exception) {
            FXUtils.showInfo( "Er is geen leerling geselecteerd :(");
        }
    }

    private void changeAttendance(ListView<Attendance> listView, AttendanceType type, String description) {
        try {
            Attendance selectedAttendance = listView.getSelectionModel().getSelectedItem();
            selectedAttendance.setType(type);
            selectedAttendance.setDescription(description);
            this.onLectureListViewClick();
        } catch (NullPointerException exception) {
            FXUtils.showInfo("Er is geen leerling geselecteerd :(");
        }
    }

    @FXML
    public void onMakeAbsentClick(ActionEvent event) {
        this.changeAttendance(this.presentListView, AttendanceType.Absent.OTHER,
                "Was niet aanwezig in de les, door de docent handmatig afgemeld.");
    }

    @FXML
    public void onMakePresentClick(ActionEvent event) {
        this.changeAttendance(this.absentListView, AttendanceType.PRESENT, "");
    }

    @FXML
    public void onDoneClick(ActionEvent event) {
        FXUtils.closeStage(event);
    }
}
