//Lusungu Ilunga - 202502786

        package com.example.hellofx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {

    @Override
    public void start(Stage stage) {

// Label
        Label message = new Label("Welcome, Lusungu Ilunga !");

// Start button
        Button startButton = new Button("Start");

// Reset button
        Button resetButton = new Button("Reset");

// Start button action
        startButton.setOnAction(event ->
                message.setText("Great! You clicked Start.")
        );

// Reset button action
        resetButton.setOnAction(event ->
                message.setText("Welcome, Your Name!")
        );

// Layout
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

// Add everything to the layout
        layout.getChildren().addAll(message, startButton, resetButton);

// Scene
        Scene scene = new Scene(layout, 500, 300);

// Window title - put your student number here
        stage.setTitle("JavaFX Lab - 202502786");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}