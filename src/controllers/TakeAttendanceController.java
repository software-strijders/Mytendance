package controllers;

import enums.AttendanceType;
import enums.SubjectType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Attendance;
import models.Class;
import models.Lecture;
import models.user.Teacher;
import models.user.User;
import utils.FXUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TakeAttendanceController {

    @FXML private ListView<Attendance> presentListView;
    @FXML private ListView<Attendance> absentListView;
    @FXML private TableView<Lecture> lectureTable;
    @FXML private TableColumn<Lecture, SubjectType> subjectColumn;
    @FXML private TableColumn<Lecture, LocalTime> startTimeColumn;
    @FXML private TableColumn<Lecture, LocalTime> endTimeColumn;
    @FXML private TableColumn<Lecture, Integer> classSizeColumn;
    @FXML private ComboBox<Class> classComboBox;
    @FXML private DatePicker datePicker;
    @FXML private Button makePresentButton;
    @FXML private Button makeAbsentButton;
    @FXML private Button showReasonButton;

    private Teacher teacher;

    @FXML
    private void initialize() {
        try {
            this.setUpUser();
            this.setUpClassComboBox();
            this.setUpListViews();
            this.setUpLectureTable();
            this.datePicker.setValue(LocalDate.now());
            this.classComboBox.setPromptText("Klas");
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

    private void setUpClassComboBox() {
        this.classComboBox.setItems(FXCollections.observableArrayList(this.teacher.getAllClasses()));
    }

    private void setUpListViews() {
        this.presentListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.absentListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.presentListView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> this.toggleAttendanceButtons(this.presentListView));
        this.absentListView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> this.toggleAttendanceButtons(this.absentListView));
    }

    private void setUpLectureTable() {
        this.subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        this.startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        this.endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        this.classSizeColumn.setCellValueFactory(new PropertyValueFactory<>("classSize"));
        this.lectureTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> this.updateListViews());
    }

    private void updateLectureTable() {
        // To-do: check if any lectures are associated with the selected class
        this.lectureTable.setItems(FXCollections.observableList(
                this.classComboBox.getValue().getLecturesByDate(this.datePicker.getValue())));
    }

    private void toggleAttendanceButtons(ListView<Attendance> listView) {
        Button toggleAttendance = this.makeAbsentButton;
        int count = listView.getSelectionModel().getSelectedItems().size();

        if (listView == this.absentListView) {
            this.showReasonButton.setDisable(count != 1);
            toggleAttendance = this.makePresentButton;
        }
        toggleAttendance.setDisable(count < 1);
    }

    @FXML
    public void onSelectionClick() {
        if (this.classComboBox.getValue() != null
                && this.datePicker.getValue() != null)
            this.updateLectureTable();
    }

    @FXML
    public void updateListViews() {
        Lecture lecture = this.lectureTable.getSelectionModel().getSelectedItem();

        if (lecture == null) {
            this.presentListView.getItems().clear();
            this.absentListView.getItems().clear();
        } else {
            Map<Boolean, List<Attendance>> attendances = lecture.getAttendances()
                    .stream().collect(Collectors.partitioningBy(Attendance::isPresent));
            this.presentListView.setItems(FXCollections.observableList(attendances.get(true)));
            this.absentListView.setItems(FXCollections.observableList(attendances.get(false)));
        }
    }

    private void changeAttendance(ListView<Attendance> listView, AttendanceType type, String description) {
        for (Attendance attendance : listView.getSelectionModel().getSelectedItems()) {
            attendance.setType(type);
            attendance.setDescription(description);
        }
        this.updateListViews();
    }

    @FXML
    public void onMakePresentClick(ActionEvent event) {
        this.changeAttendance(this.absentListView, AttendanceType.PRESENT, "");
    }

    @FXML
    public void onMakeAbsentClick(ActionEvent event) {
        this.changeAttendance(this.presentListView, AttendanceType.Absent.OTHER,
                "Was niet aanwezig in de les, door de docent handmatig afgemeld.");
    }

    @FXML
    public void onShowReasonClick(ActionEvent event) {
        FXUtils.showInfo("Reden van absentie",
                this.absentListView.getSelectionModel().getSelectedItem().getDescription());
    }
}
