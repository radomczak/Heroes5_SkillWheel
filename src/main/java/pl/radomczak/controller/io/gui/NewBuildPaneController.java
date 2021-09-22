package pl.radomczak.controller.io.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NewBuildPaneController extends GuiController{

    @FXML
    private VBox mainPane;

    @FXML
    private HBox topPane;

    @FXML
    private ChoiceBox<?> raceChoiceBox;

    @FXML
    private Button previousHero;

    @FXML
    private ImageView currentHeroImage;

    @FXML
    private Button nextHero;

    @FXML
    private ImageView raceAbilityImage1;

    @FXML
    private ImageView raceAbilityImage2;

    @FXML
    private ImageView raceAbilityImage3;

    @FXML
    private ImageView raceAbilityImage4;

    @FXML
    private Label pointsCounter;

    @FXML
    private Button saveChangesButton;

    @FXML
    private AnchorPane bottomPane;

    @FXML
    private Button pic1;

    @FXML
    private Button pic2;

    @FXML
    private ImageView raceAbilityImage41;

    @FXML
    private CheckBox pic3;
}

