package commands;

import static messages.Message.MESSAGE_INSTRUCTIONS;

import tasks.TaskList;

/**
 * This class instructs the application to list the available commands for the user.
 */
public class HelpCommand implements Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public String execute(TaskList tasks) {
        return MESSAGE_INSTRUCTIONS;
    }
}
