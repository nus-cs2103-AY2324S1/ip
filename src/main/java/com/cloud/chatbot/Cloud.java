package com.cloud.chatbot;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

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
 * The chatbot's main class.
 */
public final class Cloud {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ItemManager ITEM_MANAGER = new ItemManager();

    private static void handle(String input) {
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
            @Nullable Item item = Cloud.createItem(commandManager);
            if (item == null) {
                break;
            }

            Cloud.ITEM_MANAGER.add(item);
            Ui.say(item.toString(Cloud.ITEM_MANAGER.getCount()));
            break;
        }
        case LIST: {
            if (Cloud.ITEM_MANAGER.getCount() <= 0) {
                Ui.say("Your item list is empty.");
                break;
            }

            for (int number = 1; number <= Cloud.ITEM_MANAGER.getCount(); number++) {
                Ui.say(
                    Cloud.ITEM_MANAGER.getString(number)
                );
            }
            break;
        }
        case MARK: {
            @Nullable Integer number = Cloud.verifyNumber(commandManager);
            if (number == null) {
                break;
            }

            Cloud.ITEM_MANAGER.setComplete(number, true);
            Ui.say(
                Cloud.ITEM_MANAGER.getString(number)
            );
            break;
        }
        case UNMARK: {
            @Nullable Integer number = Cloud.verifyNumber(commandManager);
            if (number == null) {
                break;
            }

            Cloud.ITEM_MANAGER.setComplete(number, false);
            Ui.say(
                Cloud.ITEM_MANAGER.getString(number)
            );
            break;
        }
        case DELETE: {
            @Nullable Integer number = Cloud.verifyNumber(commandManager);
            if (number == null) {
                break;
            }

            Item item = Cloud.ITEM_MANAGER.remove(number);
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

    private static @Nullable Integer verifyNumber(CommandManager manager) {
        if (manager.getTokenCount() <= 1) {
            return null;
        }

        Token numberToken = manager.getToken(1);
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
     * The chatbot's main method.
     *
     * @param args Java arguments.
     */
    public static void main(String[] args) {
        Ui.say("Cloud online.");
        Ui.inputMarker();

        while (Cloud.SCANNER.hasNextLine()) {
            String input = Cloud.SCANNER.nextLine();
            Cloud.handle(input);

            Ui.inputMarker();
        }
    }
}
