package dan.command;

import dan.task.TaskList;

public class ExitCommand extends Command {
    /** Fields */
    public static final String type = "exit";

    /** Constructor */
    public ExitCommand() {
        super();
    }

    /** Methods */
    @Override
    public void op(TaskList tasks) {}

    @Override
    public String getType() {
        return type;
    }
}
