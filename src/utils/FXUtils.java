package utils;

import controllers.AddAbsenceController;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
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

    public static void showWarning(String message, Exception exception) {
        showAlert(appTitle, message, exception, Alert.AlertType.WARNING);
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
        showAlert(title, String.format("%s\n%s", message, exception.getMessage()), alertType);
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
        return loadView(title, resource, getStage(event));
    }

    public static FXMLLoader loadView(String title, String resource, Stage stage) {
        try {
            return loadComponent(title, resource, stage);
        } catch (IOException exception) {
            showWarning(exception, title);
        } catch (Exception exception) {
            showError(exception);
        } return null;
    }

    public static FXMLLoader loadComponent(String title, String resource, Event event) throws IOException {
        return loadComponent(title, resource, getStage(event));
    }

    public static FXMLLoader loadComponent(String title, String resource, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(loadResource(resource));
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(title);
        stage.setMinWidth(1025);
        stage.setMinHeight(750);

        return loader;
    }

    private static URL loadResource(String resource) throws IOException {
        URL location = FXUtils.class.getResource(resource);

        if (location == null)
            throw new IOException(String.format("Resource bestand ontbreekt: %s", resource));
        else
            return location;
    }

    public static void loadButtonComponent(String text, EventHandler<ActionEvent> event,
                                           double growSize, ObservableList<Node> childrenList, ToggleGroup group) {
        loadButtonComponent(text, event, growSize, childrenList, group, false);
    }

    public static void loadButtonComponent(String text, EventHandler<ActionEvent> event,
                                           double growSize, ObservableList<Node> childrenList,
                                           ToggleGroup group, boolean shouldBeSelected) {
        AnchorPane anchorPane = FXUtils.loadAnchorPane();
        anchorPane.setPrefWidth(150);
        anchorPane.setMaxWidth(175);
        RadioButton button = FXUtils.loadRadioButton(text, event, growSize);
        button.getStyleClass().remove(0); // Remove default styling
        button.getStyleClass().add("mytendanceButton");
        button.setToggleGroup(group);
        button.setSelected(shouldBeSelected);
        anchorPane.getChildren().add(button);
        childrenList.add(anchorPane);
    }

    public static AnchorPane loadAnchorPane() {
        return loadAnchorPane(125);
    }

    private static AnchorPane loadAnchorPane(double minWidth) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setMinWidth(minWidth);

        return anchorPane;
    }

    public static RadioButton loadRadioButton(String text, EventHandler<ActionEvent> event, double growSize) {
        RadioButton button = new RadioButton();
        button.setText(text);
        button.setOnAction(event);
        button.getStyleClass().remove(0);
        button.getStyleClass().add("mytendanceButton");
        grow(button, growSize);

        return button;
    }

    public static void loadToggleButtonComponent(String text, EventHandler<ActionEvent> event,
                                                 double growSize, ObservableList<Node> childrenList) {
        AnchorPane anchorPane = FXUtils.loadAnchorPane();
        ToggleButton button = new ToggleButton();
        button.setText(text);
        button.setOnAction(event);
        button.getStyleClass().remove(0);
        button.getStyleClass().add("toggleButton");
        anchorPane.getChildren().add(button);
        grow(button, growSize);

        childrenList.add(anchorPane);
    }

    public static void grow(Node node) {
        grow(node, 0.0);
    }

    private static void grow(Node node, double value) {
        AnchorPane.setBottomAnchor(node, value);
        AnchorPane.setTopAnchor(node, value);
        AnchorPane.setLeftAnchor(node, value);
        AnchorPane.setRightAnchor(node, value);
    }

    public static void loadPaneIntoView(AnchorPane theView, String resource) {
        try {
            Pane pane = FXMLLoader.load(loadResource(resource));
            grow(pane);
            theView.getChildren().clear(); // Before we add, it's good practice to remove the remaining nodes.
            theView.getChildren().add(pane);

            FadeTransition transition = new FadeTransition(Duration.millis(500), pane);
            transition.setFromValue(0);
            transition.setToValue(1);
            transition.play();
        } catch (IOException exception) {
            showWarning("FXML pane kan niet geladen worden :(", exception);
        } catch (Exception exception) {
            showError(exception);
        }
    }

    public static void toggleDarkMode(Node node, String resource) {
        String css = FXUtils.class.getResource(resource).toString();
        ObservableList<String> stylesheets = node.getScene().getStylesheets();

        if (stylesheets.contains(css)) {
            stylesheets.remove(css);
            return;
        }

        stylesheets.add(css);
    }
}
