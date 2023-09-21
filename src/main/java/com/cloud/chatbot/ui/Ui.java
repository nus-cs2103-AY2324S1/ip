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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * Handles the user interface (UI).
 */
public class Ui extends Application {
    private static final double WIDTH = 400;
    /** Lost width to account for on Windows 11. */
    private static final double WIDTH_OS_LOSS = 15;
    private static final double HEIGHT = 600;
    /** Lost width to account for on Windows 11. */
    private static final double HEIGHT_OS_LOSS = 40;
    private static final double PADDING = 5;

    private static final double WIDTH_SCROLL = Ui.WIDTH - (Ui.PADDING * 2);
    private static final double HEIGHT_SCROLL = Ui.HEIGHT - (Ui.PADDING * 3) - Ui.HEIGHT_BOTTOM;

    private static final double WIDTH_SEND = 50;
    private static final double WIDTH_INPUT = Ui.WIDTH - (Ui.PADDING * 3) - Ui.WIDTH_SEND;
    /** Default height of the input & button. */
    private static final double HEIGHT_BOTTOM = 25;

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

        AnchorPane.setTopAnchor(scrollPane, Ui.PADDING);
        AnchorPane.setLeftAnchor(scrollPane , Ui.PADDING);

        AnchorPane.setLeftAnchor(input , Ui.PADDING);
        AnchorPane.setBottomAnchor(input, Ui.PADDING);

        AnchorPane.setBottomAnchor(sendButton, Ui.PADDING);
        AnchorPane.setRightAnchor(sendButton, Ui.PADDING);

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
        stage.setWidth(Ui.WIDTH + Ui.WIDTH_OS_LOSS);
        stage.setHeight(Ui.HEIGHT + Ui.HEIGHT_OS_LOSS);
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
