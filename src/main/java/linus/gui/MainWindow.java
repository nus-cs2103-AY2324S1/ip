package linus.gui;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import linus.Linus;
import linus.util.Ui;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private Linus linus;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Ayaya.gif"));
    private Image linusImage = new Image(this.getClass().getResourceAsStream("/images/Linus.jpg"));
    private Image sendIcon = new Image(this.getClass().getResourceAsStream("/images/sendIcon.png"));

    /**
     * Initializes the MainWindow.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        ImageView sendIconImageView = new ImageView(sendIcon);
        sendIconImageView.setPreserveRatio(true);
        sendIconImageView.setFitWidth(sendButton.getPrefWidth() * 0.8);
        sendIconImageView.setFitHeight(sendButton.getPrefHeight() * 0.8);
        sendButton.setGraphic(sendIconImageView);
    }

    /**
     * Sets the Linus object.
     *
     * @param linus The Linus object.
     */
    public void setLinus(Linus linus) {
        this.linus = linus;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(linus.toString(), linusImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Linus's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = linus.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, linusImage)
        );

        if (response.equals(Ui.BYE_MESSAGE)) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
        userInput.clear();
    }
}
