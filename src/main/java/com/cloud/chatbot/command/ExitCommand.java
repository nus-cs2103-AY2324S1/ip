package com.cloud.chatbot.command;

import com.cloud.chatbot.token.CommandManager;
import com.cloud.chatbot.ui.CloudApp;



/**
 * Command for exiting the bot.
 */
public class ExitCommand extends Command {
    public ExitCommand(CommandManager _commandManager) {
        super(_commandManager);
    }

    @Override
    public void run() {
        CloudApp.CONTROLLER.sayBot("\\o");
        System.exit(0);
    }
}
