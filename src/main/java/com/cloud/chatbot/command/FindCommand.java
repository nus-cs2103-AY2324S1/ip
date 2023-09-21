package com.cloud.chatbot.command;

import java.util.List;

import com.cloud.chatbot.Cloud;
import com.cloud.chatbot.exception.MissingInputException;
import com.cloud.chatbot.token.CommandManager;
import com.cloud.chatbot.ui.Ui;



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
            Ui.say("Please enter a phrase to search for.");
            return;
        }

        List<String> matches = Cloud.ITEM_MANAGER.findStrings(query);
        if (matches.size() <= 0) {
            Ui.say(
                String.format(
                    "No matches were found for \"%s\". Please try a different query.",
                    query
                )
            );
            return;
        }

        for (int i = 0; i < matches.size(); i++) {
            Ui.say(
                matches.get(i)
            );
        }
    }
}
