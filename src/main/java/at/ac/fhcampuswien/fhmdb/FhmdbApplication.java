package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.data.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import static at.ac.fhcampuswien.fhmdb.ui.MovieCell.showExceptionAlert;

public class FhmdbApplication extends Application {
    @Override
    public void start(Stage stage){
        FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 890, 620);
        } catch (IOException e) {
            String title = "Error";
            String headerText = "Error while starting the application";
            String contentText = "The following error occurred while starting the application:";
            showExceptionAlert(title, headerText, contentText, e);
        }
        scene.getStylesheets().add(Objects.requireNonNull(FhmdbApplication.class.getResource("styles.css")).toExternalForm());
        stage.setTitle("FHMDb!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}