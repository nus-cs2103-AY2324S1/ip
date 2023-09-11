package com.cloud.chatbot.command;

import com.cloud.chatbot.Cloud;
import com.cloud.chatbot.Ui;
import com.cloud.chatbot.token.CommandManager;



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
            Ui.say("Your list is empty.");
            return;
        }

        for (int number = 1; number <= Cloud.ITEM_MANAGER.getCount(); number++) {
            Ui.say(
                Cloud.ITEM_MANAGER.getString(number)
            );
        }
    }
}
