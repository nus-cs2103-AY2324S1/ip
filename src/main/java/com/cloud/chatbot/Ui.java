package com.cloud.chatbot;

import com.cloud.chatbot.annotation.Nullable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;



/**
 * Handles the user interface (UI).
 */
public final class Ui extends Application {
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("o/");
        Scene scene = new Scene(helloWorld);

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
