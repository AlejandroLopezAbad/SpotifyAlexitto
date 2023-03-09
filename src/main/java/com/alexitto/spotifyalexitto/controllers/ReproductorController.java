package com.alexitto.spotifyalexitto.controllers;


import javafx.collections.MapChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;


public class ReproductorController implements Initializable {
    @FXML
    private ImageView imagenCancion;
    @FXML
    private Label cancion;
    @FXML
    private Label author;
    @FXML
    private Label playlist;
    @FXML
    private Slider barra;
    @FXML
    private Button buttonAtras;
    @FXML
    private Button buttonPlay;
    @FXML
    private Button buttonDelante;

    private MediaPlayer mediaPlayer;

    private ArrayList<String> listcanciones = new ArrayList<>();

    private Status status = Status.PAUSE;

    private int indice = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSong();
        ConfigSong(Objects.requireNonNull(getClass().getResource("/com/alexitto/spotifyalexitto/media/" + listcanciones.get(indice))));
    }


    public void ConfigSong(URL url) {
        Media media = new Media(url.toExternalForm());

        if (media.getMetadata().isEmpty()) {
            setMetaData("title", "unknown");
            setMetaData("unknown", null);
        }

        media.getMetadata().addListener((MapChangeListener<String, Object>) ch -> {
            if (ch.wasAdded()) {
                setMetaData(ch.getKey(), ch.getValueAdded());
            }
        });
        mediaPlayer = new MediaPlayer(media);
        barra.setValue(0);
        configSlider(media);


    }


    private void setMetaData(String k, Object v) {

        if (k.equals("title")) {
            cancion.setText(v.toString());
        } else if (k.equals("artist")) {
            author.setText(v.toString());
        }
        if (k.equals("album")) {
            playlist.setText(v.toString());
        }
        if (k.equals("image")){
            imagenCancion.setImage((Image) v);
        }
        if (k.equals("unknown"))
        {
            author.setText("unknown");
            playlist.setText("");
            imagenCancion.setImage(new Image(Objects.requireNonNull(ReproductorController.class.getResource("/com/alexitto/spotifyalexitto/images/music-logo-design.png")).toString()));
        }
    }

    private void configSlider(Media media) {
        barra.setOnMousePressed(event -> {
            double pos = barra.getValue();
            double totalDuration = media.getDuration().toSeconds();
            double time = (pos * totalDuration) / 100;
            mediaPlayer.seek(Duration.seconds(time));
        });

        mediaPlayer.currentTimeProperty().addListener((obs, oldVal, newVal) -> {
            double totalDuration = mediaPlayer.getMedia().getDuration().toSeconds();
            barra.setValue((newVal.toSeconds() * 100)/totalDuration);
        });
    }

    private void loadSong() {
        URL url = getClass().getResource("/com/alexitto/spotifyalexitto/media");
        String path = url.toString();
        path = path.replace("file:", "");
        File folder = new File(path);
        if (folder.exists()) {
            listcanciones.addAll(List.of(folder.list()));
        }
    }

    @FXML
    public void play() {
        if (status == Status.PLAYING) {
            mediaPlayer.pause();
            status = Status.PAUSE;

        } else if (status == Status.PAUSE) {
            mediaPlayer.play();
            status = Status.PLAYING;
        }


    }

    @FXML
    public void forward(){
        mediaPlayer.stop();
        if (indice == listcanciones.size() - 1)
            indice = 0;
        else
            indice=(indice+1);
        ConfigSong(Objects.requireNonNull(getClass().getResource("/com/alexitto/spotifyalexitto/media/" + listcanciones.get(indice))));
        mediaPlayer.play();

    }

    @FXML
    public void backward(){
        mediaPlayer.stop();
        if(indice==0)
            indice = listcanciones.size() - 1;
        else
            indice=indice-1;
        ConfigSong(Objects.requireNonNull(getClass().getResource("/com/alexitto/spotifyalexitto/media/" + listcanciones.get(indice))));
        mediaPlayer.play();

    }

    public Status getMediaPlayerStatus()
    {
        return status;
    }


}

enum Status {
    PAUSE, PLAYING, WAITING
}