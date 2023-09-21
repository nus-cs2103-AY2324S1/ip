package com.cloud.chatbot.ui;

import java.io.IOException;

import com.cloud.chatbot.annotation.Nullable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;



/**
 * Custom subclass for launching as the JavaFX Application.
 */
public class CloudApp extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(
            this.getClass().getResource("/view/Stage.fxml")
        );
        loader.setController(new StageController());
        loader.setRoot(stage);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
