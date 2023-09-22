package atlas.controllers;

import java.util.Objects;

import atlas.Atlas;
import atlas.exceptions.AtlasException;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * Atlas's GUI
 */
public class MainWindow extends AnchorPane {
    static final String USER_PROFILE_IMG_PATH = "/images/user.png";
    static final String ATLAS_PROFILE_IMG_PATH = "/images/atlas.png";

    static final double SCROLLBAR_WIDTH = 18.0;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Atlas atlas;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            USER_PROFILE_IMG_PATH)));
    private final Image atlasImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            ATLAS_PROFILE_IMG_PATH)));

    /**
     * Initialises the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        DoubleBinding dialogContainerWidth = Bindings.createDoubleBinding(()
                -> scrollPane.getWidth() - SCROLLBAR_WIDTH, scrollPane.widthProperty());
        dialogContainer.prefWidthProperty().bind(dialogContainerWidth);
        sendButton.setShape(new Circle(50));
    }

    public void setAtlas(Atlas a) {
        atlas = a;
        dialogContainer.getChildren().add(
                DialogBox.getAtlasDialog(a.getWelcome(), atlasImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Atlas's
     * reply and then appends them to the dialog container. Clears the user input after
     * processing.
     */
    @FXML
    private void handleUserInput() {
        try {
            String input = userInput.getText();
            DialogBox userDialog = DialogBox.getUserDialog(input, userImage);
            userDialog.prefWidthProperty().bind(dialogContainer.widthProperty());
            dialogContainer.getChildren().add(userDialog);

            String response = atlas.getResponse(input);
            DialogBox atlasDialog = DialogBox.getAtlasDialog(response, atlasImage);
            atlasDialog.prefWidthProperty().bind(dialogContainer.widthProperty());
            dialogContainer.getChildren().add(atlasDialog);
        } catch (AtlasException e) {
            DialogBox atlasError = DialogBox.getAtlasErrorDialog(e.getMessage(), atlasImage);
            atlasError.prefWidthProperty().bind(dialogContainer.widthProperty());
            dialogContainer.getChildren().add(atlasError);
        }
        userInput.clear();
    }

    @FXML
    private void handleButtonHoverStart() {
        sendButton.setStyle("-fx-background-color: deepskyblue;");
    }

    @FXML
    private void handleButtonHoverEnd() {
        sendButton.setStyle("-fx-background-color: cornflowerblue;");
    }
}
