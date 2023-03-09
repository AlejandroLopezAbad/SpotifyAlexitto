module com.alexitto.spotifyalexitto {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;



    opens com.alexitto.spotifyalexitto to javafx.fxml;
    exports com.alexitto.spotifyalexitto;
    exports com.alexitto.spotifyalexitto.controllers;
    opens com.alexitto.spotifyalexitto.controllers to javafx.fxml;
    opens com.alexitto.spotifyalexitto.model to javafx.fxml;
    exports com.alexitto.spotifyalexitto.model;
}