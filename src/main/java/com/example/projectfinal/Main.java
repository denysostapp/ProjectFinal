package com.example.projectfinal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainController controller = new MainController();
        BorderPane root = controller.createContent();

        primaryStage.setTitle("FootballBase");
        Scene scene = new Scene(root, 1800, 1000);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("style.css");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
