package pl.radomczak.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.radomczak.controller.WheelControl;
import pl.radomczak.controller.io.console.ConsoleUserInterface;
import pl.radomczak.controller.io.gui.StartPaneController;

public class Main extends Application{
    public static void main(String[] args) {
        //Console mode
        /*
        WheelControl wheelControl = new WheelControl();
        ConsoleUserInterface userInterface = new ConsoleUserInterface();
        userInterface.applyFor(wheelControl);
        userInterface.handle();
         */

        //Graphical mode
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        WheelControl wheelControl = new WheelControl();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/startPane.fxml"));
        VBox loginPane = loader.load();
        Scene scene = new Scene(loginPane);
        stage.setScene(scene);
        stage.setTitle("SkillWheel by Radosław Popielarski");
        StartPaneController controller = loader.getController();
        controller.passStageAndWheel(stage,wheelControl);
        stage.show();
    }
}
