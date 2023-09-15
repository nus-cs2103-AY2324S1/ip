package veneto.command;

import veneto.task.TaskList;

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

    public static String getType() {
        return type;
    }
}
