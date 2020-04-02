package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class TeacherShowLecturesController {

    @FXML private AnchorPane datePickerAnchorPane;
    @FXML private DatePicker teacherDatePicker;
    @FXML private ListView showLecturesListView;

    @FXML
    public void initialize() {
        StackPane root=new StackPane(teacherDatePicker);
        teacherDatePicker.setVisible( false );
        teacherDatePicker.setManaged( false );

        final DatePickerSkin skin = new DatePickerSkin(teacherDatePicker);
        root.getChildren().add(skin.getPopupContent());
        datePickerAnchorPane.getChildren().add(root);
        
    }

    public void onCancelPressed(ActionEvent actionEvent) {
    }

    public void onShowInfoButtonPressed(ActionEvent actionEvent) {
    }
}
