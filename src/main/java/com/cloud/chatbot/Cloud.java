package com.cloud.chatbot;

import java.util.Scanner;

import com.cloud.chatbot.item.ItemManager;



/**
 * The chatbot's main class.
 */
public final class Cloud {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ItemManager ITEM_MANAGER = new ItemManager();

    /**
     * The chatbot's main method.
     *
     * @param args Java arguments.
     */
    public static void main(String[] args) {
        Ui.say("Cloud online.");
        Ui.inputMarker();

        while (Cloud.SCANNER.hasNextLine()) {
            String input = Cloud.SCANNER.nextLine();
            Handler.handle(Cloud.ITEM_MANAGER, input);

            Ui.inputMarker();
        }
    }
}
