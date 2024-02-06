package simubank.bankapi.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.net.URI;


public class Dashboard extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new URI("file:src/main/resources/dashboard.fxml").toURL());
        Parent root = loader.load();

        stage.setScene(new Scene(root));

        stage.setMaximized(true);
        stage.setResizable(true);
        stage.setTitle("SimuBank");
        stage.show();

        stage.setOnCloseRequest((event) -> {
            System.exit(0);
        });
    }
}
