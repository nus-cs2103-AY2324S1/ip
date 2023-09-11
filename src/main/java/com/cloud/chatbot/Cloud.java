package com.cloud.chatbot;

import java.util.Scanner;

import com.cloud.chatbot.command.Dispatcher;
import com.cloud.chatbot.item.ItemManager;



/**
 * The bot's entry point.
 */
public final class Cloud {
    private static final Scanner SCANNER = new Scanner(System.in);

    /** Manages the user's Items. */
    public static final ItemManager ITEM_MANAGER = new ItemManager();

    /**
     * The bot's main loop.
     *
     * @param args Java arguments.
     */
    public static void main(String[] args) {
        Ui.say("Cloud online.");
        Ui.inputMarker();

        while (Cloud.SCANNER.hasNextLine()) {
            String input = Cloud.SCANNER.nextLine();
            Dispatcher.handle(input);

            Ui.inputMarker();
        }
    }
}
