package com.cloud.chatbot.command;

import com.cloud.chatbot.Cloud;
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
        if (Cloud.ITEM_MANAGER.getCount() <= 0) {
            CloudApp.say("Your list is empty.");
            return;
        }

        for (int number = 1; number <= Cloud.ITEM_MANAGER.getCount(); number++) {
            CloudApp.say(
                Cloud.ITEM_MANAGER.getString(number)
            );
        }
    }
}
