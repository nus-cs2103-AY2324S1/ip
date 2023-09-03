package shiba.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import shiba.ui.components.DialogNode;

/**
 * Main JavaFX window for displaying the application UI.
 */
public class MainWindow extends Application {
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 800;
    private static final int TEXT_FIELD_HEIGHT = 50;
    private static final int SEND_BUTTON_WIDTH = 100;
    private static final int WINDOW_HEIGHT_CORRECTION = 40;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new AnchorPane();
        primaryStage.setScene(new Scene(root));

        // Dialog box
        ScrollPane dialogScroll = new ScrollPane();
        dialogScroll.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT - TEXT_FIELD_HEIGHT);
        dialogScroll.setFitToWidth(true);
        dialogScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        dialogScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        VBox dialogPane = new VBox();
        dialogPane.getChildren().addAll(new DialogNode("HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello", true), new DialogNode("Hello", false));
        dialogPane.getChildren().addAll(new DialogNode("HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello", true), new DialogNode("Hello", false));
        dialogPane.getChildren().addAll(new DialogNode("HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello", true), new DialogNode("Hello", false));
        dialogPane.getChildren().addAll(new DialogNode("HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello", true), new DialogNode("Hello", false));
        dialogScroll.setContent(dialogPane);
        root.getChildren().add(dialogScroll);
        dialogPane.setPrefHeight(Region.USE_COMPUTED_SIZE);

        // Text input field
        TextField userInput = new TextField();
        userInput.setPrefSize(WINDOW_WIDTH - SEND_BUTTON_WIDTH - 3, TEXT_FIELD_HEIGHT);
        root.getChildren().add(userInput);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);

        // Send button
        Button sendButton = new Button("Send");
        sendButton.setPrefSize(SEND_BUTTON_WIDTH, TEXT_FIELD_HEIGHT);
        root.getChildren().add(sendButton);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        primaryStage.setTitle("SHIBA-BOT");
        primaryStage.setResizable(false);
        primaryStage.setMinHeight(WINDOW_HEIGHT + WINDOW_HEIGHT_CORRECTION);
        primaryStage.setMinWidth(WINDOW_WIDTH);

        root.layout();

        primaryStage.show();
    }
}
