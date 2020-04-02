package controllers;

import enums.AttendanceType;
import enums.ReasonType;
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

    @FXML private Label loggedTeacherLabel;
    @FXML private ListView<Attendance> absentListView;
    @FXML private ListView<Attendance> presentListView;
    @FXML private ListView<Lecture> lectureListView;
    @FXML private ComboBox<Class> classComboBox;
    @FXML private DatePicker attendanceDatePicker;

    private Teacher loggedInTeacher;

    @FXML
    private void initialize() throws IllegalAccessException {
        if (User.getLoggedInUser() instanceof Teacher) {
            this.loggedInTeacher = (Teacher)User.getLoggedInUser();
            this.loggedTeacherLabel.setText(this.loggedInTeacher.toString());
        } else {
            throw new IllegalAccessException("Dit is de docentenkamer, je hebt geen toestemming om hier te zijn.");
        }

        this.fillClassComboBox();
        this.attendanceDatePicker.setValue(LocalDate.now());
    }

    @FXML
    private void fillClassComboBox() {
        this.classComboBox.setItems(FXCollections.observableArrayList(this.loggedInTeacher.getAllClasses()));
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

            for (Attendance attendance : selectedLecture.getAttendances()) {
                if (attendance.getAttendanceType().equals(AttendanceType.PRESENT))
                    presentStudents.add(attendance);
                else if (attendance.getAttendanceType().equals(AttendanceType.ABSENT))
                    absentStudents.add(attendance);
            }
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
            FXUtils.showInfo("Reden van absentie", selectedAbsent.getReasonDescription());
        } catch (NullPointerException e) {
            FXUtils.showInfo( "Er is geen leerling geselecteerd :(");
        }
    }

    private void changeAttendance(ListView<Attendance> listView, ReasonType reason, String reasonDescription, AttendanceType attendanceType) {
        try {
            Attendance selectedAttendance = listView.getSelectionModel().getSelectedItem();
            selectedAttendance.setReason(reason);
            selectedAttendance.setReasonDescription(reasonDescription);
            selectedAttendance.setAttendanceType(attendanceType);
            this.onLectureListViewClick();
        } catch (NullPointerException exception) {
            FXUtils.showInfo("Er is geen leerling geselecteerd :(");
        }
    }

    @FXML
    public void onMakeAbsentClick(ActionEvent event) {
        String reasonDescription = "Was niet aanwezig in de les, door de docent handmatig afgemeld.";
        this.changeAttendance(this.presentListView, ReasonType.OTHER, reasonDescription, AttendanceType.ABSENT);
    }

    @FXML
    public void onMakePresentClick(ActionEvent event) {
        this.changeAttendance(this.absentListView, null, null, AttendanceType.PRESENT);
    }

    @FXML
    public void onDoneClick(ActionEvent event) {
        FXUtils.closeStage(event);
    }
}
