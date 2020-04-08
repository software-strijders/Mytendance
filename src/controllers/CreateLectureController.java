package controllers;

import enums.SubjectType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Attendance;
import models.Class;
import models.Lecture;
import models.user.Student;
import models.user.Teacher;
import models.user.User;
import utils.FXUtils;
import utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class CreateLectureController {

    @FXML private TitledPane lectureTitlePane;
    @FXML private TableView<Lecture> lectureTable;
    @FXML private TableColumn<Lecture, SubjectType> subjectColumn;
    @FXML private TableColumn<Lecture, LocalTime> startColumn;
    @FXML private TableColumn<Lecture, LocalTime> endColumn;
    @FXML private TableColumn<Lecture, Class> classColumn;
    @FXML private DatePicker startDatePicker;
    @FXML private ComboBox<Class> classComboBox;
    @FXML private ComboBox<SubjectType> subjectComboBox;
    @FXML private Spinner<Integer> hourSpinner;
    @FXML private Spinner<Integer> minuteSpinner;
    @FXML private Spinner<Integer> durationSpinner;

    private Teacher teacher;
    private Class selectedClass;
    private SubjectType selectedSubjectType;
    private LocalDate selectedDate;

    @FXML
    private void initialize() {
        try {
            this.setUpUser();
            this.setUpSpinners();
            this.setUpFactory();
            this.setUpListeners();
            this.setUpLectureTable();

            this.lectureTitlePane.setText("Geen datum geselecteerd");
            this.classComboBox.setPromptText("Klas");
            this.classComboBox.setItems(FXCollections.observableArrayList(this.teacher.getAllClasses()));
            this.subjectComboBox.setPromptText("Vak");
            this.subjectComboBox.setItems(FXCollections.observableArrayList(SubjectType.values()));
        } catch (IllegalAccessException exception) {
            FXUtils.showInfo(exception.getMessage());
        } catch (Exception exception) {
            FXUtils.showError(exception);
        }
    }

    private void setUpUser() throws IllegalAccessException {
        if (User.getLoggedInUser() instanceof Teacher)
            this.teacher = (Teacher) User.getLoggedInUser();
        else
            throw new IllegalAccessException("Dit scherm is alleen toegankelijk voor docenten :(");
    }

    private void setUpSpinners() {
        LocalDateTime now = LocalDateTime.now();
        SpinnerValueFactory<Integer> hourFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(0, 23, now.getHour());
        SpinnerValueFactory<Integer> minuteFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(0, 59, now.getMinute());
        SpinnerValueFactory<Integer> durationFactory = new SpinnerValueFactory
                .IntegerSpinnerValueFactory(60, 240, 60);

        this.minuteSpinner.setEditable(true);
        this.hourSpinner.setEditable(true);
        this.durationSpinner.setEditable(true);

        this.hourSpinner.setValueFactory(hourFactory);
        this.minuteSpinner.setValueFactory(minuteFactory);
        this.durationSpinner.setValueFactory(durationFactory);
    }

    private void setUpFactory() {
        // We don't want lessons that are in the past (might change, though).
        this.startDatePicker.setDayCellFactory(datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || item.compareTo(today) < 0);
            }
        });
    }

    private void setUpListeners() {
        this.classComboBox.valueProperty().addListener((observableValue, oldValue, newValue) ->
                this.updateLectureTable());
        this.startDatePicker.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            this.updateLectureTable();
            this.updateLectureTitlePane();
        });
    }

    private void updateLectureTitlePane() {
        LocalDate date = this.startDatePicker.getValue();
        if (date == null)
            return; // We don't want to update the titledPane when this is the case.

        LocalDateTime dateTime = date.atStartOfDay().withHour(this.hourSpinner.getValue())
                .withMinute(this.minuteSpinner.getValue());
        this.lectureTitlePane.setText(Utils.formatDateTime(dateTime, "d-M-y"));
    }

    private void setUpLectureTable() {
        this.subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        this.startColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        this.endColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        this.classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        this.updateLectureTable();
    }

    private void updateLectureTable() {
        this.selectedClass = this.classComboBox.getValue();
        this.selectedDate = this.startDatePicker.getValue();

        if (this.selectedClass == null || this.selectedDate == null)
            return; // We don't want to update the list when the user hasn't selected anything yet.

        this.lectureTable.setItems(FXCollections.observableArrayList(
                this.selectedClass.getLecturesByDate(this.selectedDate)));
    }

    @FXML
    private void onLectureAddClick(ActionEvent event) {
        try {
            this.obtainVariables();
            this.createLecture();
        } catch (InputMismatchException | IllegalArgumentException exception) {
            FXUtils.showInfo(exception.getMessage());
        } catch (Exception exception) {
            FXUtils.showError(exception);
        }
    }

    private void obtainVariables() throws InputMismatchException {
        this.selectedClass = this.classComboBox.getValue();
        this.selectedSubjectType = this.subjectComboBox.getValue();
        this.selectedDate = this.startDatePicker.getValue();

        if (this.selectedClass == null)
            throw new InputMismatchException("Er is geen klas geselecteerd :(");
        else if (this.selectedSubjectType == null)
            throw new InputMismatchException("Er is geen vak geselecteerd :(");
        else if (this.selectedDate == null)
            throw new InputMismatchException("Er is geen datum geselecteerd :(");
        else if (LocalDate.now().isAfter(this.selectedDate))
            throw new InputMismatchException("Een les kan niet in het verleden worden aangemaakt :(");
    }

    private void createLecture() throws IllegalArgumentException {
        int hours = this.hourSpinner.getValue();
        int minutes = this.minuteSpinner.getValue();
        int duration = this.durationSpinner.getValue();
        LocalDateTime date = this.selectedDate.atStartOfDay().withHour(hours).withMinute(minutes);
        Lecture lecture = new Lecture(date, duration, this.selectedSubjectType,
                this.teacher, this.selectedClass, null); // CLEAN THIS UP

        Lecture.addLecture(lecture); // If duplicate or within time range, this will throw the exception.
        this.teacher.addLecture(lecture);
        this.selectedClass.addLecture(lecture);
        lecture.setAttendances(createAttendances(lecture));

        this.lectureTable.setItems(FXCollections.observableArrayList(
                this.selectedClass.getLecturesByDate(this.selectedDate)));
        FXUtils.showInfo("De nieuwe les is aangemaakt :)");
    }

    private List<Attendance> createAttendances(Lecture lecture) {
        // TODO: in the future, when a student can toggle their absence, we should check if it's toggled here:
        List<Attendance> attendances = new ArrayList<>();

        for (Student student : this.classComboBox.getValue().getStudents()) {
            Attendance attendance = new Attendance(lecture, student);
            attendances.add(attendance);
            Attendance.addAttendance(attendance);
        }
        return attendances;
    }
}
