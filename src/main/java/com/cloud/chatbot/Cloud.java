package com.cloud.chatbot;

import com.cloud.chatbot.annotation.Nullable;
import com.cloud.chatbot.ui.CloudApp;

import javafx.application.Application;



/**
 * The bot's entry point.
 */
public final class Cloud {
    /**
     * The main method which launches the JavaFX UI.
     *
     * @param args Java arguments.
     */
    public static void main(String[] args) {
        System.out.println("Launching...");
        Application.launch(CloudApp.class, args);
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
