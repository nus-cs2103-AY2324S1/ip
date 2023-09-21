package com.cloud.chatbot;

import com.cloud.chatbot.item.ItemManager;
import com.cloud.chatbot.ui.CloudApp;

import javafx.application.Application;



/**
 * The bot's entry point.
 */
public final class Cloud {
    /** Manages the user's Items. */
    public static final ItemManager ITEM_MANAGER = new ItemManager();

    /**
     * The main method which launches the JavaFX UI.
     *
     * @param args Java arguments.
     */
    public static void main(String[] args) {
        System.out.println("Launching...");
        Application.launch(CloudApp.class, args);
    }
}
