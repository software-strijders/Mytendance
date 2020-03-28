package controllers;

import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Utils;
import java.io.IOException;

public class MenuController {

    public void onUserCreatePressed(ActionEvent event) throws IOException {
        Stage stage = Utils.loadStage("Voeg gebruiker toe",
                "/views/AddUser.fxml", Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void onClassCreateButtonPressed(ActionEvent event) throws IOException {
        Stage stage = Utils.loadStage("Klas aanmaken",
                "/views/CreateClassWindow.fxml", Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }
}
