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
import java.net.URL;

public class FXUtils {

    private static final String appTitle = "Mytendance";

    public static void showError(String message, Exception exception) {
        showError(appTitle, message, exception);
    }

    public static void showError(String title, String message, Exception exception) {
        showAlert(title, message, exception, Alert.AlertType.ERROR);
    }

    public static void showWarning(String message, Exception exception) {
        showWarning(appTitle, message, exception);
    }

    public static void showWarning(String title, String message, Exception exception) {
        showAlert(title, message, exception, Alert.AlertType.WARNING);
    }

    public static void showInfo(String message) {
        showInfo(appTitle, message);
    }

    public static void showInfo(String title, String message) {
        showAlert(title, message, Alert.AlertType.INFORMATION);
    }

    public static void showAlert(String title, String message, Exception exception, Alert.AlertType alertType) {
        showAlert(title, String.format("%s\n(%s)", message, exception.getMessage()), alertType);
    }

    public static void showAlert(String message, Alert.AlertType alertType) {
        showAlert(appTitle, message, alertType);
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
        return loadStage(appTitle, resource, stage, modality);
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
        return loadStage(appTitle, resource);
    }

    public static Stage loadStage(String resource, Stage stage) throws IOException {
        return loadStage(appTitle, resource, stage);
    }

    public static Stage loadStage(String title, String resource) throws IOException {
        return loadStage(title, resource, new Stage());
    }

    // This method might be deemed unnecessary once the testing phases are completed
    public static Stage loadStage(String title, String resource, Stage stage) throws IOException {
        Parent root = FXMLLoader.load(loadResource(resource));
        stage.setScene(new Scene(root));
        stage.setTitle(title);

        return stage;
    }

    public static FXMLLoader loadComponent(String title, String resource, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(loadResource(resource));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(title);

        return loader;
    }

    private static URL loadResource(String resource) throws IOException {
        URL location = FXUtils.class.getResource(resource);

        if (location == null)
            throw new IOException(String.format("Resource bestand ontbreekt: %s", resource));
        else
            return location;
    }
}
