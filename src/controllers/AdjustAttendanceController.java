package controllers;

import enums.AttendanceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import models.Attendance;
import models.Lecture;
import models.user.Student;
import models.user.User;
import models.Class;

import java.util.Arrays;

public class AdjustAttendanceController {

    @FXML private ListView<Attendance> attendanceList;
    @FXML private ComboBox<AttendanceType> reasonBox;
    @FXML private TextArea descriptionBox;

    private Student loggedInStudent;

    @FXML
    public void initialize() throws IllegalAccessException {
        if (User.getLoggedInUser() instanceof Student) {
            this.loggedInStudent = (Student)User.getLoggedInUser();
        } else {
            throw new IllegalAccessException("U heeft niet de bevoegde rol voor deze informatie :(");
        }
        fillReasonBox();
        fillAttendanceList();
    }

    @FXML
    private void fillReasonBox() {
        this.reasonBox.setItems(FXCollections.observableArrayList(Arrays.asList(AttendanceType.Absent.values())));
    }

    @FXML
    private void fillAttendanceList() {
        attendanceList.setItems(FXCollections.observableArrayList(Attendance.getAttendancesbyStudent(loggedInStudent)));
    }

    @FXML
    private void onConfirmButtonClick(ActionEvent event) {
        String description = descriptionBox.getText();
        Attendance selectedAttendance = attendanceList.getSelectionModel().getSelectedItem();
        AttendanceType selectedType = reasonBox.getValue();

        selectedAttendance.setDescription(description);
        selectedAttendance.setType(selectedType);
        clearFields();
    }

    @FXML
    private void onMakePresentButtonClick(ActionEvent event) {
        Attendance selectedAttendance = attendanceList.getSelectionModel().getSelectedItem();
        selectedAttendance.setDescription("");
        selectedAttendance.setType(AttendanceType.PRESENT);
        clearFields();
    }

    private void clearFields() {
        descriptionBox.setText("");
        reasonBox.setValue(null);
        fillAttendanceList();
    }
}
