package com.cloud.chatbot.command;

import com.cloud.chatbot.token.CommandManager;
import com.cloud.chatbot.ui.CloudApp;



/**
 * Command for listing all Items.
 */
public class ListCommand extends Command {
    public ListCommand(CommandManager _commandManager) {
        super(_commandManager);
    }

    @Override
    public void run() {
        if (CloudApp.ITEM_MANAGER.getCount() <= 0) {
            CloudApp.CONTROLLER.sayBot("Your list is empty.");
            return;
        }

        for (int number = 1; number <= CloudApp.ITEM_MANAGER.getCount(); number++) {
            CloudApp.CONTROLLER.sayBot(
                CloudApp.ITEM_MANAGER.getString(number)
            );
        }
    }
}
