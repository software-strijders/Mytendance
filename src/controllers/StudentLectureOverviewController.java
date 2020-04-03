package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;
import models.Attendance;
import utils.FXUtils;
import models.Class;

public class StudentLectureOverviewController {
    private Class selectedClass;

    @FXML
    public void initialize() {
        datePicker.setVisible(false);
        datePicker.setManaged(false);
        DatePickerSkin skin = new DatePickerSkin(datePicker);
        datePickerAnchorPane.getChildren().add(skin.getPopupContent());
    }

    @FXML
    private AnchorPane datePickerAnchorPane;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ListView<?> showLecturesListView;

    @FXML
    void onCancelPressed(ActionEvent event) {
        FXUtils.loadView("Student menu", "/views/StudentMenu.fxml", event);
    }

    @FXML
    void onShowInfoButtonPressed(ActionEvent event) {
//        FXUtils.showInfo("Les informatie", );
    }

    private void updateLectureListView() {


       // this.selectedClass =
                //zoek waar leerling in welke klas die klas is het


//        this.selectedClass = this.classComboBox.getValue();
//        this.selectedDate = this.startDatePicker.getValue();
//
//        if (this.selectedClass == null || this.selectedDate == null)
//            return; // We don't want to update the list when the user hasn't selected anything yet.
//
//        this.lectureListView.setItems(FXCollections.observableArrayList(
//                this.selectedClass.getLecturesByDateTime(this.selectedDate)));
    }

}
