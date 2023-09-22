package com.cloud.chatbot.command;

import java.util.List;

import com.cloud.chatbot.exception.MissingInputException;
import com.cloud.chatbot.token.CommandManager;
import com.cloud.chatbot.ui.CloudApp;



/**
 * Command for finding Items by searching details for an exact match.
 */
public class FindCommand extends Command {
    public FindCommand(CommandManager _commandManager) {
        super(_commandManager);
    }

    @Override
    public void run() {
        String query;
        try {
            query = this.commandManager.getDetails();
        } catch (MissingInputException e) {
            CloudApp.CONTROLLER.sayBot("Please enter a phrase to search for.");
            return;
        }

        List<String> matches = CloudApp.ITEM_MANAGER.findStrings(query);
        if (matches.size() <= 0) {
            CloudApp.CONTROLLER.sayBot(
                String.format(
                    "No matches were found for \"%s\". Please try a different query.",
                    query
                )
            );
            return;
        }

        for (int i = 0; i < matches.size(); i++) {
            CloudApp.CONTROLLER.sayBot(
                matches.get(i)
            );
        }
    }
}
