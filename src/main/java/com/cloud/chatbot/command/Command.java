package com.cloud.chatbot.command;

import com.cloud.chatbot.Cloud;
import com.cloud.chatbot.Ui;
import com.cloud.chatbot.annotation.Nullable;
import com.cloud.chatbot.token.CommandManager;
import com.cloud.chatbot.token.Token;



/**
 * Parent Command class which all runnable Commands inherit from.
 */
public abstract class Command {
    CommandManager commandManager;

    protected @Nullable Integer verifyNumber() {
        if (commandManager.getTokenCount() <= 1) {
            return null;
        }

        Token numberToken = commandManager.getToken(1);
        if (!numberToken.isInt()) {
            Ui.say(
                String.format(
                    "\"%s\" is not a valid number.",
                    numberToken.toString()
                )
            );
            return null;
        }

        if (!numberToken.isValidNumber(Cloud.ITEM_MANAGER.getCount())) {
            Ui.say(
                String.format(
                    "Item #%d does not exist.",
                    numberToken.toInt()
                )
            );
            return null;
        }

        return numberToken.toInt();
    }

    /**
     * Creates a Command for an instance of user input.
     *
     * @param commandManager The input.
     */
    public Command(CommandManager _commandManager) {
        this.commandManager = _commandManager;
    }

    /**
     * Runs the command.
     */
    public abstract void run();
}
