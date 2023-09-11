package com.cloud.chatbot;

import com.cloud.chatbot.annotations.Nullable;



/**
 * Handles interactions with the user.
 */
public final class Ui {
    /**
     * Displays output to the user.
     *
     * @param message The output.
     */
    public static void say(String message) {
        System.out.println(message);
    }

    /**
     * Displays an input marker indicating the bot is waiting for a command.
     */
    public static void inputMarker() {
        System.out.print("\n>>> ");
    }

    public static void error(String message) {
        error(message, null);
    }

    /**
     * Displays an error message alongside an object.
     *
     * @param message The error.
     * @param object The object to display.
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
