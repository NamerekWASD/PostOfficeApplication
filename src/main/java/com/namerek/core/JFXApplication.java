package com.namerek.core;

import com.namerek.core.PresentationLayer.View;
import com.namerek.core.PresentationLayer.ViewSwitcher;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;


public class JFXApplication extends Application {
    private Scene scene;

    public static void main(String[] args) {
        Application.launch();
    }
    @Override
    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("static/login.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        scene = new Scene(root, 1400, 800);
    }
    @Override
    public void start(Stage stage){
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.LOGIN);
        stage.setScene(scene);
        stage.setTitle("Post office");
        stage.show();
    }
}