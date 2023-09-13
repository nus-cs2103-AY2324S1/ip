package dan.command;

import dan.task.TaskList;

public class ListCommand extends Command {
    /** Fields */
    public static final String type = "list";

    /** Constructor */
    public ListCommand() {
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
