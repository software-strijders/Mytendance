package utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FXUtils {

    public static void showAlert(String message, Alert.AlertType alertType) {
        showAlert("Mytendance", message, alertType);
    }

    public static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(message);
        alert.setTitle(title);
        alert.show();
    }

    public static Stage loadStage(String resource, Modality modality) throws IOException {
        return loadStage(resource, new Stage(), modality);
    }

    public static Stage loadStage(String resource, Stage stage, Modality modality) throws IOException {
        return loadStage("Mytendance", resource, stage, modality);
    }

    public static Stage loadStage(String title, String resource, Modality modality) throws IOException {
        return loadStage(title, resource, new Stage(), modality);
    }

    public static Stage loadStage(String title, String resource, Stage stage, Modality modality) throws IOException {
        loadStage(title, resource, stage);
        stage.initModality(modality);

        return stage;
    }

    public static Stage loadStage(String resource) throws IOException {
        return loadStage("Mytendance", resource);
    }

    public static Stage loadStage(String resource, Stage stage) throws IOException {
        return loadStage("Mytendance", resource, stage);
    }

    public static Stage loadStage(String title, String resource) throws IOException {
        return loadStage(title, resource, new Stage());
    }

    // This method might be deemed unnecessary once the testing phases are completed
    public static Stage loadStage(String title, String resource, Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Utils.class.getResource(resource));
        stage.setScene(new Scene(root));
        stage.setTitle(title);

        return stage;
    }

    public static FXMLLoader loadComponent(String title, String resource, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Utils.class.getResource(resource));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(title);

        return loader;
    }
}
