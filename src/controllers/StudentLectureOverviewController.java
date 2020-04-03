package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;

public class StudentLectureOverviewController {

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
    }

    @FXML
    void onShowInfoButtonPressed(ActionEvent event) {
    }

}
