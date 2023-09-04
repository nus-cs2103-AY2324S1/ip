package commands;

import tasks.TaskList;
import static messages.Message.MESSAGE_INSTRUCTIONS;

/**
 * This class handles all unsupported or unknown commands the user inputs.
 */
public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList tasks) {
        System.out.println("Unknown input! Please type a proper command.");
        System.out.println(MESSAGE_INSTRUCTIONS);
    }
}
