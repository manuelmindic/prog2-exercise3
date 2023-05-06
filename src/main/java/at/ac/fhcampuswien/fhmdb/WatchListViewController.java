package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.api.MovieAPI;
import at.ac.fhcampuswien.fhmdb.data.WatchlistMovieEntity;
import at.ac.fhcampuswien.fhmdb.data.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortedState;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.w3c.dom.Entity;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class WatchListViewController {

    @FXML
    public Button homeBtn;
    @FXML
    public Button watchlistBtn;
    @FXML
    public Button aboutBtn;

    @FXML
    public VBox mainBox;

    WatchlistRepository repository;

    @FXML
    public JFXListView movieListView;


    public void initialize(){
        System.out.println("WatchList Controller initialized");
        repository = new WatchlistRepository();
        List<WatchlistMovieEntity> movieEntities = new ArrayList<>();

        try {
            movieEntities = repository.readAllMovies();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (WatchlistMovieEntity element : movieEntities){
            System.out.println(element);
        }

        ObservableList<Movie> observableMovies = FXCollections.observableArrayList(
                movieEntities.stream()
                        .map(WatchlistMovieEntity::createMovie)
                        .collect(Collectors.toList()));


        movieListView.setItems(observableMovies);   // set the items of the listview to the observable list
        movieListView.setCellFactory(movieListView -> new MovieCell("Remove")); // apply custom cells to the listview
    }


    public void loadView(String path){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(path));

        try {
            mainBox.getChildren().clear();
            mainBox.getChildren().add(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void loadHome() {
        loadView("home-view.fxml");
    }

    public void loadWatchList() {
        loadView("watch-list-view.fxml");

    }

    public void loadAbout() {
        loadView("watch-list-view.fxml");

    }
}
