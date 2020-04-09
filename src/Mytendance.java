import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.data.VolatileData;
import utils.FXUtils;

public class Mytendance extends Application {

    public static final double WIDTH = 1025;
    public static final double HEIGHT = 750;

    public static void main(String[] args) {
        VolatileData.createData();
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        FXUtils.loadView("Selecteer rol", "/views/RoleSelection.fxml", stage);
        stage.show();
        stage.setMinWidth(Mytendance.WIDTH);
        stage.setMinHeight(Mytendance.HEIGHT);
        stage.getIcons().add(new Image("/views/style/images/logo.png"));
    }
}
