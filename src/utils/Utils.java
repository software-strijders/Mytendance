package utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.UUID;

public class Utils {

    public static UUID idGenerator(){
        return UUID.randomUUID();
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
}
