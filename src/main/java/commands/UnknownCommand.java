package commands;

import tasks.TaskList;
import static messages.Message.MESSAGE_INSTRUCTIONS;

public class UnknownCommand extends Command {

    @Override
    public void execute(TaskList tasks) {
        System.out.println("Unknown input! Please type a proper command.");
        System.out.println(MESSAGE_INSTRUCTIONS);
    }
}
