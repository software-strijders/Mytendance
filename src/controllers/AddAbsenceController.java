package controllers;

import enums.AttendanceType;
import enums.ReasonType;
import enums.SubjectType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import models.Attendance;
import models.Class;
import models.Lecture;
import models.user.Student;
import models.user.User;
import utils.FXUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

public class AddAbsenceController {

        @FXML private ComboBox<ReasonType> reasonComboBox;
        @FXML private TextArea descriptionBox;
        @FXML private ComboBox<SubjectType> subjectComboBox;
        @FXML private DatePicker datedatePicker;
        @FXML private ListView<Lecture> lectureListView;
        private Student loggedInStudent;

        @FXML
        public void initialize() throws IllegalAccessException {
            if (User.getLoggedInUser() instanceof Student) {
                this.loggedInStudent = (Student)User.getLoggedInUser();
            } else {
                throw new IllegalAccessException("U heeft niet de bevoegde rol voor deze informatie :(");
            }
            datedatePicker.setValue(LocalDate.now());
            fillSubjectComboBox();
            fillReasonComboBox();
        }

        @FXML
        private void fillSubjectComboBox() {
            this.subjectComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(SubjectType.values())));
        }

        @FXML
        private void fillReasonComboBox() {
            this.reasonComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(ReasonType.values())));
        }

        @FXML
        private void onCancelClick(ActionEvent event) {
            FXUtils.loadView("Hoofdmenu", "/views/StudentMenu.fxml", event);
        }

        @FXML
        private void clearFields() {
            datedatePicker.setValue(LocalDate.now());
            subjectComboBox.setValue(SubjectType.OOP);
            reasonComboBox.setValue(ReasonType.ILL);
            descriptionBox.setText("");
        }

        @FXML
        private void onConfirmClick(ActionEvent event) throws Exception {
            Lecture selectedLecture = lectureListView.getSelectionModel().getSelectedItem();
            ReasonType selectedReason = reasonComboBox.getValue();
            String reasonDescription = descriptionBox.getText();

            try {
                if (reasonDescription.isEmpty()) {
                    throw new Exception();
                }
                for (Attendance attendance : selectedLecture.getAttendances()) {
                    if (attendance.getStudent().equals(loggedInStudent)) {
                            attendance.setAttendanceType(AttendanceType.ABSENT);
                            attendance.setReasonDescription(reasonDescription);
                            attendance.setReason(selectedReason);
                            FXUtils.showInfo("De absentiemelding is aangemaakt :)");
                            clearFields();
                    }
                }
            } catch (Exception e) {
                FXUtils.showWarning("Fout!","Vul alle velden in :/");
            }
        }

        @FXML
        public void onSubjectComboBoxClick(ActionEvent event) {
            SubjectType selectedSubject = subjectComboBox.getValue();
            LocalDate selectedDate = datedatePicker.getValue();
            ObservableList<Lecture> allLectures = FXCollections.observableArrayList();

            for (Class class_ : loggedInStudent.getClasses()) {
                for (Lecture lecture : class_.getLectures()) {
                    if (lecture.getSubjectType().equals(selectedSubject)
                            && lecture.getStartDate().toLocalDate().isEqual(selectedDate)) {
                        allLectures.add(lecture);
                    }
                }
            }
            lectureListView.setItems(allLectures);
        }
}
