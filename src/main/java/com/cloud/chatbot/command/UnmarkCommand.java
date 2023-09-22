package com.cloud.chatbot.command;

import com.cloud.chatbot.annotation.Nullable;
import com.cloud.chatbot.token.CommandManager;
import com.cloud.chatbot.ui.CloudApp;



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

        CloudApp.ITEM_MANAGER.setComplete(number, false);
        CloudApp.CONTROLLER.sayBot(
            CloudApp.ITEM_MANAGER.getString(number)
        );
    }
}
