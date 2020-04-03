package utils;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public final class FXUtils {

    /**
     * We should not be able to make an instance of this class
     */
    private FXUtils() {}

    private static final String appTitle = "Mytendance";

    public static void showError(Exception exception) {
        showError("Er is iets goed misgegaan x(", exception);
    }

    public static void showError(String message, Exception exception) {
        showError(appTitle, message, exception);
    }

    public static void showError(String title, String message, Exception exception) {
        showAlert(title, message, exception, Alert.AlertType.ERROR);
    }

    public static void showWarning(Exception exception, String view) {
        showWarning(appTitle, String.format("FXML view '%s' kan niet geladen worden :(", view), exception);
    }

    public static void showWarning(String message) {
        showWarning(appTitle, message);
    }

    public static void showWarning(String title, String message) {
        showAlert(title, message, Alert.AlertType.WARNING);
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

    public static Stage loadStage(String title, String resource, Modality modality) throws IOException {
        return loadStage(title, resource, new Stage(), modality);
    }

    public static Stage loadStage(String title, String resource, Stage stage, Modality modality) throws IOException {
        loadStage(title, resource, stage);
        stage.initModality(modality);

        return stage;
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

    private static Node getNode(Event event) {
        return (Node)event.getSource();
    }

    private static Stage getStage(Event event) {
        return getStage(getNode(event));
    }

    private static Stage getStage(Node node) {
        return (Stage)node.getScene().getWindow();
    }

    public static void closeStage(Event event) {
        getStage(event).close();
    }

    public static void closeStage(Node node) {
        getStage(node).close();
    }

    public static void loadView(String title, String resource, boolean blockInput) {
        try {
            if (blockInput)
                loadStage(title, resource, Modality.APPLICATION_MODAL).showAndWait();
            else
                loadStage(title, resource).show();
        } catch (IOException exception) {
            showWarning(exception, title);
        } catch (Exception exception) {
            showError(exception);
        }
    }

    public static FXMLLoader loadView(String title, String resource, Event event) {
        try {
            return loadComponent(title, resource, event);
        } catch (IOException exception) {
            showWarning(exception, title);
        } catch (Exception exception) {
            showError(exception);
        } return null;
    }

    public static FXMLLoader loadComponent(String title, String resource, Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(loadResource(resource));
        Stage stage = getStage(event);
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
