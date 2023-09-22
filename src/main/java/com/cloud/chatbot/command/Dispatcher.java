package com.cloud.chatbot.command;

import com.cloud.chatbot.exception.MissingInputException;
import com.cloud.chatbot.token.CommandManager;
import com.cloud.chatbot.ui.CloudApp;



/**
 * Handles commands from the user, reacting accordingly.
 */
public final class Dispatcher {
    /**
     * Handles an instance of user input by dispatching the appropriate Command.
     *
     * @param input The input.
     */
    public static void handle(String input) {
        CommandManager commandManager = new CommandManager(input);
        String command;
        try {
            command = commandManager.getCommand();
        } catch (MissingInputException e) {
            // Ignore empty inputs
            return;
        }

        CloudApp.CONTROLLER.sayUser(input);

        switch (CommandType.fromString(command)) {
        case ADD:
            new AddCommand(commandManager).run();
            break;
        case LIST:
            new ListCommand(commandManager).run();
            break;
        case FIND:
            new FindCommand(commandManager).run();
            break;
        case MARK:
            new MarkCommand(commandManager).run();
            break;
        case UNMARK:
            new UnmarkCommand(commandManager).run();
            break;
        case DELETE:
            new DeleteCommand(commandManager).run();
            break;
        case EXIT:
            new ExitCommand(commandManager).run();
            break;
        default:
            CloudApp.CONTROLLER.sayBot(
                String.format(
                    "\"%s\" is not a valid command.",
                    command
                )
            );
            break;
        }
    }
}
