package com.cloud.chatbot.ui;

import com.cloud.chatbot.annotation.Nullable;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * Custom subclass for launching as the JavaFX Application.
 */
public class CloudApp extends Application {
    public static final double WIDTH = 400;
    /** Lost width to account for on Windows 11. */
    public static final double WIDTH_OS_LOSS = 15;
    public static final double HEIGHT = 600;
    /** Lost width to account for on Windows 11. */
    public static final double HEIGHT_OS_LOSS = 40;
    public static final double PADDING = 5;

    public static final double WIDTH_SCROLL = CloudApp.WIDTH - (CloudApp.PADDING * 2);
    public static final double HEIGHT_SCROLL = CloudApp.HEIGHT - (CloudApp.PADDING * 3) - CloudApp.HEIGHT_BOTTOM;

    public static final double WIDTH_SEND = 50;
    public static final double WIDTH_INPUT = CloudApp.WIDTH - (CloudApp.PADDING * 3) - CloudApp.WIDTH_SEND;
    /** Default height of the input & button. */
    public static final double HEIGHT_BOTTOM = 25;

    @Override
    public void start(Stage stage) {
        // Create node structure
        VBox messagesHolder = new VBox();
        ScrollPane scrollPane = new ScrollPane(messagesHolder);

        TextField input = new TextField();
        Button sendButton = new Button("Send");

        AnchorPane anchorPane = new AnchorPane(scrollPane, input, sendButton);

        Scene scene = new Scene(anchorPane);

        // Format nodes
        scrollPane.setPrefSize(WIDTH_SCROLL, HEIGHT_SCROLL);
        scrollPane.setFitToWidth(true);

        input.setPrefWidth(WIDTH_INPUT);
        sendButton.setPrefWidth(WIDTH_SEND);

        AnchorPane.setTopAnchor(scrollPane, CloudApp.PADDING);
        AnchorPane.setLeftAnchor(scrollPane , CloudApp.PADDING);

        AnchorPane.setLeftAnchor(input , CloudApp.PADDING);
        AnchorPane.setBottomAnchor(input, CloudApp.PADDING);

        AnchorPane.setBottomAnchor(sendButton, CloudApp.PADDING);
        AnchorPane.setRightAnchor(sendButton, CloudApp.PADDING);

        // Set handler callbacks
        EventHandler<Event> handleSend = (event) -> {
            Label labelUser = new Label(input.getText());
            Label labelCloud = new Label("OK.");

            messagesHolder.getChildren().addAll(
                new MessageRow(labelUser, true),
                new MessageRow(labelCloud, false)
            );
            input.clear();
        };
        sendButton.setOnMouseClicked(handleSend);
        input.setOnAction((event) -> handleSend.handle(event));

        messagesHolder.heightProperty().addListener(
            (observable) -> {
                // Scroll down whenever height changes
                scrollPane.setVvalue(1);
            }
        );

        // Show when done
        stage.setTitle("Cloud");
        stage.setWidth(CloudApp.WIDTH + CloudApp.WIDTH_OS_LOSS);
        stage.setHeight(CloudApp.HEIGHT + CloudApp.HEIGHT_OS_LOSS);
        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Prints the specified message.
     *
     * @param message The message.
     */
    public static void say(String message) {
        System.out.println(message);
    }

    /**
     * Prints an input marker indicating the bot is awaiting a command.
     */
    public static void inputMarker() {
        System.out.print("\n>>> ");
    }

    public static void error(String message) {
        error(message, null);
    }

    /**
     * Prints an error message to standard error, alongside an optional object.
     *
     * @param message The error.
     * @param object The object to print.
     */
    public static void error(String message, @Nullable Object object) {
        System.err.println(
            String.format(
                "ERR %s!",
                message
            )
        );

        if (object != null) {
            System.err.println(object);
        }
    }
}
