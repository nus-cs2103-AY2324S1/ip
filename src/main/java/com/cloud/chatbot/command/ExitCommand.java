package com.cloud.chatbot.command;

import com.cloud.chatbot.Ui;
import com.cloud.chatbot.token.CommandManager;



/**
 * Command for exiting the bot.
 */
public class ExitCommand extends Command {
    public ExitCommand(CommandManager _commandManager) {
        super(_commandManager);
    }

    @Override
    public void run() {
        Ui.say("\\o");
        System.exit(0);
    }
}
