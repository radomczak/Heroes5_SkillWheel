package pl.radomczak.controller.io.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class StartPaneController extends GuiController{

    @FXML
    private Button viewBuildsButton;

    @FXML
    private Button createBuildButton;

    public void initialize()
    {
        addEvents();
    }

    private void addEvents() {
        viewBuildsButton.addEventHandler(ActionEvent.ACTION,actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/viewBuildsPane.fxml"));
            VBox mainPane = null;
            try {
                mainPane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ViewBuildsPaneController controller = loader.getController();
            controller.passStageAndWheel(stage,wheelControl);
            Scene scene = new Scene(mainPane);
            stage.setScene(scene);
        });

        createBuildButton.addEventHandler(ActionEvent.ACTION,actionEvent -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/newBuildPane.fxml"));
            VBox mainPane = null;
            try {
                mainPane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            NewBuildPaneController controller = loader.getController();
            controller.passStageAndWheel(stage,wheelControl);
            Scene scene = new Scene(mainPane);
            stage.setScene(scene);
        });
    }
}
