package com.ducky.controller;

import com.ducky.common.Ducky;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String USER_IMG_PATH = "/images/User.png";
    private static final String DUCKY_IMG_PATH = "/images/Ducky.png";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Ducky ducky;

    private Image userImage = new Image(this.getClass().getResourceAsStream(USER_IMG_PATH));
    private Image duckyImage = new Image(this.getClass().getResourceAsStream(DUCKY_IMG_PATH));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDucky(Ducky d) {
        ducky = d;
        String loadResult = ducky.loadSavedTasks();
        dialogContainer.getChildren().add(
                DialogBox.getDuckyDialog(loadResult, duckyImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ducky.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDuckyDialog(response, duckyImage)
        );
        userInput.clear();
    }
}
