package duke;

import java.util.Objects;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Represents the user interface of an application.
 */
public class UserInterface {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private final Duke duke;
    private Image userImg;
    private Image dukeImg;
    public UserInterface(Duke duke) {
        this.duke = duke;
    }

    /**
     * Initializes the images into the interface.
     * @param userImagePath relative path of the user image.
     * @param dukeImagePath relative path of the duke image.
     */
    public void init(String userImagePath, String dukeImagePath) {
        try {
            assert (userImagePath != null && dukeImagePath != null); // Means the arguments are passed wrongly.
            this.userImg = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(userImagePath)));
            this.dukeImg = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(dukeImagePath)));
        } catch (NullPointerException e) {
            this.userImg = null;
            this.dukeImg = null;
        }
    }
    /**
     * Creates the scene that we want to use to represent the ui.
     * @return the ui scene.
     */
    public Scene makeScene() {
        spawnUiElements();
        setUiSettings();
        setAnchorPaneSettings();
        addUiFunctionality();
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        return new Scene(mainLayout);
    }

    /**
     * Sends the input to the associated chatbot, for it to handle and reply (or not.)
     */
    public void input() {
        String input = userInput.getText();
        assert (input != null);
        Label inputLabel = new Label(input);
        DialogBox userDialog = DialogBox.getUserDialog(inputLabel, constrUserIV());
        dialogContainer.getChildren().add(userDialog);
        duke.handle(input);
        userInput.clear();
    }

    /**
     * Outputs a string to the user interface.
     * @param output the string to be printed.
     */
    public void output(String output) {
        assert (output != null);
        Label dukeLabel = new Label(output);
        DialogBox dukeDialog = DialogBox.getDukeDialog(dukeLabel, constrDukeIV());
        dialogContainer.getChildren().add(dukeDialog);
    }

    private ImageView constrDukeIV() {
        if (dukeImg == null) {
            return new ImageView();
        } else {
            return new ImageView(dukeImg);
        }
    }
    private ImageView constrUserIV() {
        if (userImg == null) {
            return new ImageView();
        } else {
            return new ImageView(userImg);
        }
    }

    /**
     * Creates the ui elements and saves them to the instance's variables.
     */
    private void spawnUiElements() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
    }

    /**
     * Sets the parameters of the ui elements.
     */
    private void setUiSettings() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Sets the AnchorPane settings.
     */
    private void setAnchorPaneSettings() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Adds any ui functionalities and interactables.
     */
    private void addUiFunctionality() {
        sendButton.setOnMouseClicked((event) -> input());
        userInput.setOnAction((event) -> input());
    }
}
