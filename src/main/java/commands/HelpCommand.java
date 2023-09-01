package commands;

import tasks.TaskList;
import static messages.Message.MESSAGE_INSTRUCTIONS;

public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    @Override
    public void execute(TaskList tasks) {
        System.out.println(MESSAGE_INSTRUCTIONS);
    }
}
