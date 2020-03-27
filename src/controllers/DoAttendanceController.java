package controllers;

import javafx.fxml.FXML;
import enums.ReasonType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import java.time.LocalDate;

public class DoAttendanceController {

    @FXML public ListView absentListView;
    @FXML public ListView presentListView;
    @FXML public ListView lectureListView;
    @FXML public ComboBox classComboBox;
    @FXML public ComboBox standardReasonTypeComboBox;
    @FXML public DatePicker attendanceDatePicker;
    @FXML public Button makeAbsentButton;
    @FXML public Button makePresentButton;
    @FXML public Button doneButton;
    @FXML public Button CancelButton;

    @FXML
    public void initialize() {
        fillClassComboBox();
        fillStandardReasonTypeComboBox();
        standardReasonTypeComboBox.setValue(ReasonType.PRESENT);
        attendanceDatePicker.setValue(LocalDate.now());
    }

    @FXML
    public void fillClassComboBox() {
        //dit moet de classcombobox vullen met alle klassen van de huidige docent
        ObservableList<Class> allClasses = FXCollections.observableArrayList();
        //allClasses.addAll(Class.getAllClasses());
        classComboBox.setItems(allClasses);
    }

    @FXML
    public void fillStandardReasonTypeComboBox() {
        ObservableList<ReasonType> reasons = FXCollections.observableArrayList();
        reasons.add(ReasonType.PRESENT);
        reasons.add(ReasonType.ILL);
        reasons.add(ReasonType.DENTIST);
        reasons.add(ReasonType.DOCTOR);
        reasons.add(ReasonType.MARRIAGE);
        reasons.add(ReasonType.ENTOMBMENT);
        reasons.add(ReasonType.FUNERAL);
        reasons.add(ReasonType.OTHER);
        standardReasonTypeComboBox.setItems(reasons);
    }

    @FXML
    public void fillLectureListView() {
        //ObservableList<Lecture> allLectures = FXCollections.observableArrayList();
        //geeft alle lessen in listview als klas is geselecteerd
    }

    @FXML
    public void fillAbsentListView() {
        //ObservableList<Student> absentStudents = FXCollections.observableArrayList();
        //vult alle absente leerlingen van geselecteerde klas
    }

    @FXML
    public void fillPresentListView() {
        //ObservableList<Student> presentStudents = FXCollections.observableArrayList();
        //vult alle presente leerlingen van geselecteerde klas
    }

    @FXML
    void onMakeAbsentClick(ActionEvent event) {
    }

    @FXML
    void onMakePresentClick(ActionEvent event) {
    }

    @FXML
    public void onDoneClick(ActionEvent actionEvent) {
        //save changes
    }

    @FXML
    public void onCancelClick(ActionEvent actionEvent) {
        initialize();
    }
}


