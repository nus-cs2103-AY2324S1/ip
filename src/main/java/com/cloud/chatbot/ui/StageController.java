package com.cloud.chatbot.ui;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * The Stage controller for the JavaFX Application.
 */
public class StageController extends Stage {
    @FXML private Stage stage;
    @FXML private Scene scene;
    @FXML private AnchorPane anchorPane;
    @FXML private ScrollPane scrollPane;
    @FXML private VBox messagesHolder;
    @FXML private TextField input;
    @FXML private Button sendButton;

    /**
     * Called by FXML as part of its lifecycle, after the view has been loaded.
     *
     * An instance is constructed in our code to be set as an FXMLLoader's
     * controller. This is before that loader's load() gets called, meaning this
     * instance's @FXML fields would not have been populated yet.
     *
     * Therefore, code that manipulates Nodes needs to be here and not in a
     * constructor.
     */
    @FXML public void initialize() {
        // Add listener to scroll down whenever height changes, such as when a
        // a new message is added
        messagesHolder.heightProperty().addListener(
            (observable) -> {
                scrollPane.setVvalue(1);
            }
        );
    }

    @FXML private void handleSend() {
        messagesHolder.getChildren().addAll(
            new MessageRow(input.getText(), true),
            new MessageRow("OK.", false)
        );

        input.clear();
    }
}
