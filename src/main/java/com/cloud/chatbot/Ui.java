package com.cloud.chatbot;

import com.cloud.chatbot.annotation.Nullable;



/**
 * Handles the user interface (UI).
 */
public final class Ui {
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
