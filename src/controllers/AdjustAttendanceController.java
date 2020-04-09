package controllers;

import enums.AttendanceType;
import enums.SubjectType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Attendance;
import models.user.Student;
import models.user.User;
import utils.FXUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class AdjustAttendanceController {

    @FXML private TableView<Attendance> attendanceTable;
    @FXML private TableColumn<Attendance, SubjectType> subjectColumn;
    @FXML private TableColumn<Attendance, AttendanceType> reasonColumn;
    @FXML private TableColumn<Attendance, LocalTime> timeColumn;
    @FXML private ComboBox<AttendanceType> reasonBox;
    @FXML private DatePicker datePicker;
    @FXML private TextArea descriptionBox;

    private Student loggedInStudent;

    @FXML
    public void initialize() throws IllegalAccessException {
        if (User.getLoggedInUser() instanceof Student) {
            this.loggedInStudent = (Student)User.getLoggedInUser();
        } else {
            throw new IllegalAccessException("U heeft niet de bevoegde rol voor deze informatie :(");
        }
        datePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate tomorrow = LocalDate.now().plusDays(1);

                setDisable(empty || date.compareTo(tomorrow) < 0 );
            }
        });
        datePicker.setValue(LocalDate.now().plusDays(0));
        setUpAttendanceTable();
        fillReasonBox();
    }

    private void setUpAttendanceTable() {
        this.timeColumn.setCellValueFactory(new PropertyValueFactory<>("lectureDate"));
        this.subjectColumn.setCellValueFactory(new PropertyValueFactory<>("lectureSubject"));
        this.reasonColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.attendanceTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            updateInfo();
        });

        updateAttendanceTable();
    }

    private void updateInfo() {
        Attendance attendance = attendanceTable.getSelectionModel().getSelectedItem();

        if (attendance != null) {
            reasonBox.setValue(attendance.getType());
            descriptionBox.setText(attendance.getDescription());
        }
    }

    private void updateAttendanceTable() {
        ArrayList<Attendance> attendances = new ArrayList<>();
        for(Attendance attendance : Attendance.getAttendancesByStudent(loggedInStudent)) {
            if (attendance.getLecture().getStartDate().toLocalDate().isEqual(datePicker.getValue())
                    && !attendance.isPresent()) {
                attendances.add(attendance);
            }
        }
        attendanceTable.setItems(FXCollections.observableList(attendances));
    }

    @FXML
    private void fillReasonBox() {
        this.reasonBox.setItems(FXCollections.observableArrayList(Arrays.asList(AttendanceType.Absent.values())));
    }

    @FXML
    private void onConfirmButtonClick(ActionEvent event) {
        String description = descriptionBox.getText();
        Attendance selectedAttendance = attendanceTable.getSelectionModel().getSelectedItem();
        AttendanceType selectedType = reasonBox.getValue();

        if (selectedAttendance == null) {
            FXUtils.showWarning("Geen absentiemelding geselecteerd :/");
            return;
        }
        if (description.equals("")) {
            FXUtils.showInfo("Beschrijving is niet ingevuld :/");
            return;
        }
        selectedAttendance.setDescription(description);
        selectedAttendance.setType(selectedType);
        FXUtils.showInfo("Absentie aangepast.");
        updateAttendanceTable();
    }

    @FXML
    private void onDatePickerAction() {
        updateAttendanceTable();
    }

    @FXML
    private void onMakePresentButtonClick(ActionEvent event) {
        Attendance selectedAttendance = attendanceTable.getSelectionModel().getSelectedItem();

        if(selectedAttendance == null) {
            FXUtils.showWarning("Geen absentiemelding geselecteerd :/");
            return;
        }
        selectedAttendance.setDescription("");
        selectedAttendance.setType(AttendanceType.PRESENT);
        FXUtils.showInfo("Absentiemelding verwijderd. Je bent weer present gemeld :)");
        clearFields();
    }

    private void clearFields() {
        descriptionBox.clear();
        reasonBox.setValue(null);
        setUpAttendanceTable();
    }
}
