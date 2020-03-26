package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Utils;

import java.io.IOException;

public class MenuController {

    public void onUserCreatePressed(ActionEvent actionEvent) throws IOException {
        Stage newStage = Utils.loadStage("/views/AddUser.fxml");
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.showAndWait();
    }

    public void onClassCreateButtonPressed(ActionEvent actionEvent) throws IOException {
        Stage newStage = Utils.loadStage("/views/CreateClassWindow.fxml");
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setTitle("Klas aanmaken");
        newStage.showAndWait();
    }
}
