package commands;

import tasks.TaskList;
import static messages.Message.MESSAGE_INSTRUCTIONS;

/**
 * This class instructs the application to list the available commands for the user.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public void execute(TaskList tasks) {
        System.out.println(MESSAGE_INSTRUCTIONS);
    }
}
