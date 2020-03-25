package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    public void onUserCreatePressed(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/views/AddUser.fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.showAndWait();

    }

    public void onClassCreateButtonPressed(ActionEvent actionEvent) throws IOException {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/views/CreateClass.fxml")); // doesn't exist yet
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.showAndWait();
    }
}
