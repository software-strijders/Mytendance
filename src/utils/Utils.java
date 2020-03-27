package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static UUID idGenerator(){
        return UUID.randomUUID();
    }

    public static void showAlert(String message, Alert.AlertType alertType) {
        showAlert("Mytendance", message, alertType);
    }

    public static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.show();
    }

    public static void makeAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(message);
        alert.setHeaderText(message);
        alert.show();
    }

    public static boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    public static String formatClassName(String field, int studyYear, char group) {
        return String.format("%s-V%d%s", field, studyYear, group);
    }

    public static Stage loadStage(String resourceName, String title, Modality modality) throws IOException {
        Parent root = FXMLLoader.load(Utils.class.getResource(resourceName));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.initModality(modality);
        return stage;
    }

    public static char getCharFromStringByIndex(String string, int index) {
        if (string.isEmpty()) {
            return '0'; // Unsure what to return here
        }

        return string.charAt(index);
    }

    // Author: Jason Buberel
    public static final Pattern EMAIL_ADDR_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    // Author: Jason Buberel
    public static boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_ADDR_REGEX.matcher(email);
        return matcher.find();
    }

    // This method might be removed once the testing phases are completed
    public static Stage loadScreen(String title, String resource) throws IOException {
        return loadScreen(title, resource, new Stage());
    }

    // This method might be removed once the testing phases are completed
    public static Stage loadScreen(String title, String resource, Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Utils.class.getResource(resource));
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();

        return stage;
    }

    public static FXMLLoader loadComponent(String title, String resource, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Utils.class.getResource(resource));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle(title);
        stage.show();

        return loader;
    }
}
