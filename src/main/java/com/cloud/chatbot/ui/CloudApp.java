package com.cloud.chatbot.ui;

import java.io.IOException;

import com.cloud.chatbot.Cloud;
import com.cloud.chatbot.item.ItemManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;



/**
 * Custom subclass for launching as the JavaFX Application.
 *
 * Serves as the central class which holds all shared instances.
 */
public class CloudApp extends Application {
    /**
     * Bridges the logic and the UI.
     *
     * Controller cannot be constructed in Cloud as JavaFX disallows doing so on
     * the main thread.
     */
    public static final StageController CONTROLLER = new StageController();

    /** Manages the user's Items. */
    public static final ItemManager ITEM_MANAGER = new ItemManager();

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(
            this.getClass().getResource("/view/Stage.fxml")
        );
        loader.setController(CloudApp.CONTROLLER);
        loader.setRoot(stage);
        try {
            loader.load();
        } catch (IOException e) {
            Cloud.error(
                "Could not load Stage.fxml",
                e
            );
            return;
        }

        stage.show();
    }
}
