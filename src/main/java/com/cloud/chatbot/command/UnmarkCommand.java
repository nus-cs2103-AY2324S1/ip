package com.cloud.chatbot.command;

import com.cloud.chatbot.Cloud;
import com.cloud.chatbot.Ui;
import com.cloud.chatbot.annotation.Nullable;
import com.cloud.chatbot.token.CommandManager;



/**
 * Command for marking an Item as incomplete.
 */
public class UnmarkCommand extends Command {
    public UnmarkCommand(CommandManager _commandManager) {
        super(_commandManager);
    }

    @Override
    public void run() {
        @Nullable Integer number = this.verifyNumber();
        if (number == null) {
            return;
        }

        Cloud.ITEM_MANAGER.setComplete(number, false);
        Ui.say(
            Cloud.ITEM_MANAGER.getString(number)
        );
    }
}
