package com.alexitto.spotifyalexitto.controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainViewController {

    @FXML
    private ReproductorController reproductorController;

    @FXML
    private VBox sideMenu;

    @FXML
    private ImageView menuButton;

    @FXML
    private void onMenuOpen()
    {

        sideMenu.setPrefWidth(Region.USE_COMPUTED_SIZE);
        sideMenu.setVisible(true);
        menuButton.setVisible(false);
    }

    @FXML
    private void onMenuClose()
    {
        sideMenu.setVisible(false);
        sideMenu.setPrefWidth(0);
        menuButton.setVisible(true);
    }

    @FXML
    private void onOpenSongButtonClicked()
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select one song");
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("MP3", "*.mp3")
        );
        try {
            File song = chooser.showOpenDialog(null);
            if (song != null)
            {
                if (reproductorController.getMediaPlayerStatus() == Status.PLAYING)
                    reproductorController.play();
                reproductorController.ConfigSong(song.toURI().toURL());
                reproductorController.play();
            }

        }catch (MalformedURLException e) {
            System.err.println("Wrong URL");
        }

    }

    @FXML
    private void onPreferencesButtonClicked()
    {
        //TODO optional
    }

    @FXML
    private void onExitButtonClicked()
    {
        System.exit(0);
    }
}
