package cyrus.commands;

import cyrus.parser.ParseInfo;
import cyrus.tasks.TaskList;

/**
 * Command to exit the program.
 */
public class ByeCommand extends Command {
    public ByeCommand(TaskList taskList, ParseInfo parseInfo) {
        super(taskList, parseInfo);
    }

    @Override
    public String[] execute() throws CommandError {
        return new String[]{
            "Bye. Hope to see you again!"
        };
    }
}
