package com.cloud.chatbot.command;

import com.cloud.chatbot.annotation.Nullable;
import com.cloud.chatbot.item.Item;
import com.cloud.chatbot.token.CommandManager;
import com.cloud.chatbot.ui.CloudApp;



/**
 * Command for deleting an Item.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(CommandManager _commandManager) {
        super(_commandManager);
    }

    @Override
    public void run() {
        @Nullable Integer number = this.verifyNumber();
        if (number == null) {
            return;
        }

        Item item = CloudApp.ITEM_MANAGER.remove(number);
        CloudApp.CONTROLLER.sayBot("Yeeted:");
        CloudApp.CONTROLLER.sayBot(item.toString(number));
    }
}
