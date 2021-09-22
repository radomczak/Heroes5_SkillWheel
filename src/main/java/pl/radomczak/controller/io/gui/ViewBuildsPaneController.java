package pl.radomczak.controller.io.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ViewBuildsPaneController extends GuiController {

    @FXML
    private VBox viewPane;

    @FXML
    private HBox topPane;

    @FXML
    private ChoiceBox<?> raceChoiceBox;

    @FXML
    private ChoiceBox<?> buildChoiceBox;

    @FXML
    private ImageView currentHero;

    @FXML
    private ImageView raceAbilityImage1;

    @FXML
    private ImageView raceAbilityImage2;

    @FXML
    private ImageView raceAbilityImage3;

    @FXML
    private ImageView raceAbilityImage4;

    @FXML
    private Button modifyButton;

    @FXML
    private Button goBackButton;

    @FXML
    private AnchorPane bottomPane;

}

