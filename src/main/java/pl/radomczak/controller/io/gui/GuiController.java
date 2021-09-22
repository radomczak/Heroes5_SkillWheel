package pl.radomczak.controller.io.gui;

import javafx.stage.Stage;
import pl.radomczak.controller.WheelControl;

public class GuiController {
    protected Stage stage;
    protected WheelControl wheelControl;

    public void passStageAndWheel(Stage stage, WheelControl wheelControl) {
        this.stage = stage;
        this.wheelControl = wheelControl;
    }
}
