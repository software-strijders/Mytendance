package utils;

import javafx.scene.control.Alert;

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
}
