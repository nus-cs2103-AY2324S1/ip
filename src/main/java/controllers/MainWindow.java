package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.GigaChadBot;
import parser.Parser;

import java.io.IOException;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private GigaChadBot corubi;
    private final Parser parser = new Parser();


    private final Image user = new Image(this.getClass().getResourceAsStream("/images/idol.png"));
    private final Image bot = new Image(this.getClass().getResourceAsStream("/images/chad.png"));


    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setBot(GigaChadBot d) throws IOException {
        corubi = d;
        String NAME = "GigaChadbot";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(corubi.getStore().load(parser), bot),
                DialogBox.getDukeDialog("Hello! I am " + NAME + ". \nWhat can I do for you?", bot)
);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = corubi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user),
                DialogBox.getDukeDialog(response, bot)
        );
        if (input.equalsIgnoreCase("bye")) {
            System.exit(0);
        }
        userInput.clear();
    }
}
