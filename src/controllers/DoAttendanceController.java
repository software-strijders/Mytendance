package controllers;

import enums.AttendanceType;
import enums.ReasonType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Attendance;
import models.Class;
import models.Lecture;
import models.user.Teacher;
import models.user.User;
import utils.FXUtils;

import java.time.LocalDate;

public class DoAttendanceController {

    private Teacher t1;

    @FXML private Label loggedTeacherLabel;
    @FXML private ListView<Attendance> absentListView;
    @FXML private ListView<Attendance> presentListView;
    @FXML private ListView<Lecture> lectureListView;
    @FXML private ComboBox<Class> classComboBox;
    @FXML private DatePicker attendanceDatePicker;
    @FXML private Button doneButton;

    @FXML
    private void initialize() throws Exception {
        User tempUser = User.getLoggedInUser();
        if (tempUser instanceof Teacher) {
            t1 = (Teacher)tempUser;
            loggedTeacherLabel.setText(t1.toString());
        } else {
            throw new Exception("Dit is de docentenkamer, je hebt geen toestemming om hier te zijn.");
        }
        
        fillClassComboBox();
        attendanceDatePicker.setValue(LocalDate.now());
    }

    @FXML
    private void fillClassComboBox() {
          classComboBox.setItems(FXCollections.observableArrayList(t1.getAllClasses()));
    }

    @FXML
    public void onClassComboBoxClick() {
        LocalDate selectedDate = attendanceDatePicker.getValue();
        Class selectedClass = classComboBox.getValue();

        ObservableList<Lecture> allLecturesFromSelectedDate = FXCollections.observableArrayList();

        for (Lecture lecture : selectedClass.getLectures()) {
            if (lecture.getStartDate().isEqual(selectedDate.atStartOfDay())) {
                allLecturesFromSelectedDate.add(lecture);
            }
        }
        lectureListView.setItems(allLecturesFromSelectedDate);
    }

    @FXML
    public void onLectureListViewClick() {
        try {
            ObservableList<Attendance> absentStudents = FXCollections.observableArrayList();
            ObservableList<Attendance> presentStudents = FXCollections.observableArrayList();
            Lecture selectedLecture = lectureListView.getSelectionModel().getSelectedItem();

            for (Attendance attendance : selectedLecture.getAttendances()) {
                if (attendance.getAttendanceType().equals(AttendanceType.PRESENT))
                    presentStudents.add(attendance);
                else if (attendance.getAttendanceType().equals(AttendanceType.ABSENT))
                    absentStudents.add(attendance);
            }
            absentListView.setItems(absentStudents);
            presentListView.setItems(presentStudents);
        } catch (NullPointerException e) {
            FXUtils.showAlert("Er is geen les geselecteerd!", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    public void onShowReasonClick(ActionEvent event) {
        try {
            Attendance selectedAbsent = absentListView.getSelectionModel().getSelectedItem();
            FXUtils.showAlert("Reden van absentie",selectedAbsent.getReasonDescription(), Alert.AlertType.INFORMATION);
        } catch (NullPointerException e) {
            FXUtils.showAlert( "Er is geen leerling geselecteerd!", Alert.AlertType.INFORMATION);
        }
    }

    private void changeAttendance(ListView<Attendance> listView, ReasonType reason, String reasonDescription, AttendanceType attendanceType) {
        try {
            Attendance selectedAttendance = listView.getSelectionModel().getSelectedItem();
            selectedAttendance.setReason(reason);
            selectedAttendance.setReasonDescription(reasonDescription);
            selectedAttendance.setAttendanceType(attendanceType);
            onLectureListViewClick();
        } catch (NullPointerException e) {
            FXUtils.showAlert("Er is geen leerling geselecteerd!", Alert.AlertType.INFORMATION);
        }
    }

    @FXML
    public void onMakeAbsentClick(ActionEvent event) {
        String reasonDescription = "Was niet aanwezig in de les, door de docent handmatig afgemeld.";
        changeAttendance(presentListView, ReasonType.OTHER, reasonDescription, AttendanceType.ABSENT);
    }

    @FXML
    public void onMakePresentClick(ActionEvent event) {
        changeAttendance(absentListView, null, null, AttendanceType.PRESENT);
    }

    @FXML
    public void onDoneClick(ActionEvent actionEvent) {
        Stage stage = (Stage) doneButton.getScene().getWindow();
        stage.close();
    }
}
