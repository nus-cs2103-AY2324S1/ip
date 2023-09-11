package com.cloud.chatbot.command;

import java.time.format.DateTimeParseException;

import com.cloud.chatbot.Cloud;
import com.cloud.chatbot.DateConverter;
import com.cloud.chatbot.Ui;
import com.cloud.chatbot.annotation.Nullable;
import com.cloud.chatbot.exception.IllegalTimestampException;
import com.cloud.chatbot.exception.MissingFlagInputException;
import com.cloud.chatbot.exception.MissingInputException;
import com.cloud.chatbot.item.Deadline;
import com.cloud.chatbot.item.Event;
import com.cloud.chatbot.item.Item;
import com.cloud.chatbot.item.Task;
import com.cloud.chatbot.token.CommandManager;
import com.cloud.chatbot.token.FlagManager;



/**
 * Command for adding new Items.
 */
public class AddCommand extends Command {
    private @Nullable Item createItem() {
        String description;
        try {
            description = this.commandManager.getDetails();
        } catch (MissingInputException e) {
            Ui.say("Please enter a description for your item.");
            return null;
        }

        @Nullable FlagManager managerBy = this.commandManager.findFlag("by");
        @Nullable FlagManager managerFrom = this.commandManager.findFlag("from");
        @Nullable FlagManager managerTo = this.commandManager.findFlag("to");

        try {
            if (managerBy != null) {
                return new Deadline(
                    description,
                    DateConverter.timestampToInstant(managerBy.getSubInput())
                );
            }
            if (managerFrom != null && managerTo != null) {
                return new Event(
                    description,
                    DateConverter.timestampToInstant(managerFrom.getSubInput()),
                    DateConverter.timestampToInstant(managerTo.getSubInput())
                );
            }
        } catch (MissingFlagInputException e) {
            Ui.say(
                String.format(
                    "Please enter a description for the \"%s\" flag.",
                    e.getFlagText()
                )
            );
            return null;
        } catch (DateTimeParseException e) {
            Ui.say("Please use a valid timestamp format.");
            return null;
        } catch (IllegalTimestampException e) {
            Ui.say("Please enter a logical timestamp range.");
            return null;
        }

        return new Task(description);
    }

    public AddCommand(CommandManager _commandManager) {
        super(_commandManager);
    }

    @Override
    public void run() {
        @Nullable Item item = this.createItem();
        if (item == null) {
            return;
        }

        Cloud.ITEM_MANAGER.add(item);
        Ui.say(item.toString(Cloud.ITEM_MANAGER.getCount()));
    }
}
