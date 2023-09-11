package com.cloud.chatbot;

import java.time.format.DateTimeParseException;

import com.cloud.chatbot.annotations.Nullable;
import com.cloud.chatbot.exceptions.IllegalTimestampException;
import com.cloud.chatbot.exceptions.MissingFlagInputException;
import com.cloud.chatbot.exceptions.MissingInputException;
import com.cloud.chatbot.item.Deadline;
import com.cloud.chatbot.item.Event;
import com.cloud.chatbot.item.Item;
import com.cloud.chatbot.item.ItemManager;
import com.cloud.chatbot.item.Task;
import com.cloud.chatbot.token.CommandManager;
import com.cloud.chatbot.token.FlagManager;
import com.cloud.chatbot.token.Token;



/**
 * Handles commands from the user, reacting accordingly.
 */
public final class Handler {
    private static @Nullable Item createItem(CommandManager manager) {
        String description;
        try {
            description = manager.getDetails();
        } catch (MissingInputException e) {
            Ui.say("Please enter a description for your item.");
            return null;
        }

        @Nullable FlagManager managerBy = manager.findFlag("by");
        @Nullable FlagManager managerFrom = manager.findFlag("from");
        @Nullable FlagManager managerTo = manager.findFlag("to");

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

    private static @Nullable Integer verifyNumber(
        CommandManager commandManager,
        ItemManager itemManager
    ) {
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

        if (!numberToken.isValidNumber(itemManager.getCount())) {
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
     * The main handler logic based on the instance of user input.
     *
     * @param itemManager The bot's ItemManager.
     * @param input The raw input.
     */
    public static void handle(ItemManager itemManager, String input) {
        CommandManager commandManager = new CommandManager(input);
        String command;
        try {
            command = commandManager.getCommand();
        } catch (MissingInputException e) {
            // Ignore empty inputs
            return;
        }

        switch (CommandType.fromString(command)) {
        case ADD: {
            @Nullable Item item = Handler.createItem(commandManager);
            if (item == null) {
                break;
            }

            itemManager.add(item);
            Ui.say(item.toString(itemManager.getCount()));
            break;
        }
        case LIST: {
            if (itemManager.getCount() <= 0) {
                Ui.say("Your item list is empty.");
                break;
            }

            for (int number = 1; number <= itemManager.getCount(); number++) {
                Ui.say(
                    itemManager.getString(number)
                );
            }
            break;
        }
        case MARK: {
            @Nullable Integer number = Handler.verifyNumber(
                commandManager,
                itemManager
            );
            if (number == null) {
                break;
            }

            itemManager.setComplete(number, true);
            Ui.say(
                itemManager.getString(number)
            );
            break;
        }
        case UNMARK: {
            @Nullable Integer number = Handler.verifyNumber(
                commandManager,
                itemManager
            );
            if (number == null) {
                break;
            }

            itemManager.setComplete(number, false);
            Ui.say(
                itemManager.getString(number)
            );
            break;
        }
        case DELETE: {
            @Nullable Integer number = Handler.verifyNumber(
                commandManager,
                itemManager
            );
            if (number == null) {
                break;
            }

            Item item = itemManager.remove(number);
            Ui.say("Yeeted:");
            Ui.say(item.toString(number));
            break;
        }
        case EXIT: {
            Ui.say("\\o");
            System.exit(0);
            break;
        }
        default: {
            Ui.say(
                String.format(
                    "\"%s\" is not a valid command.",
                    command
                )
            );
            break;
        }
        }
    }
}
