package duke;

import dukeexception.CorruptedFileException;
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
    public void init(String userImagePath, String dukeImagePath) throws CorruptedFileException {
        try {
            this.userImg = new Image(this.getClass().getResourceAsStream(userImagePath));
            this.dukeImg = new Image(this.getClass().getResourceAsStream(dukeImagePath));
        } catch (NullPointerException e) {
            throw new CorruptedFileException();
        }
    }
    public Scene sceneMaker() {
        spawnUiElements();
        setUiSettings();
        setAnchorPaneSettings();
        addUiFunctionality();
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        return new Scene(mainLayout);
    }

    public void input() {
        String input = userInput.getText();
        Label inputLabel = new Label(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(inputLabel, new ImageView(userImg)));
        duke.handle(input);
        userInput.clear();
    }

    /**
     * Takes a string and prints it out to the system, while formatting it with line dividers.
     * @param output the string to be printed.
     */
    public void output(String output) {
        Label dukeLabel = new Label(output);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeLabel, new ImageView(dukeImg)));
    }

    private void spawnUiElements() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
    }
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
    private void setAnchorPaneSettings() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }
    private void addUiFunctionality() {
        sendButton.setOnMouseClicked((event) -> input());
        userInput.setOnAction((event) -> input());
    }

}
