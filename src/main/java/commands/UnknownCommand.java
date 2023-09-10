package commands;

import tasks.TaskList;
import static messages.Message.MESSAGE_INSTRUCTIONS;

/**
 * This class handles all unsupported or unknown commands the user inputs.
 */
public class UnknownCommand extends Command {

    @Override
    public String execute(TaskList tasks) {
        String message = "";
        message += "Unknown input! Please type a proper command.\n" + MESSAGE_INSTRUCTIONS;

        return message;
    }
}
