import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Mytendance extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("views/AddUser.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Add new user");
        stage.setScene(scene);
        stage.show();

//        String fxmlPage = "views/AddUser.fxml";
//        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPage));
//        Parent root = loader.load();
//        stage.setTitle("Add new user");
//        stage.setScene(new Scene(root));
//        stage.show();

    }
}
