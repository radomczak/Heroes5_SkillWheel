module Heroes5.SkillWheel {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports pl.radomczak.app to javafx.graphics;
    opens pl.radomczak.controller.io.gui to javafx.fxml, javafx.base;
}