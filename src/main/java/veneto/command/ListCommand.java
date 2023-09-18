package veneto.command;

import veneto.task.TaskList;

public class ListCommand extends Command {
    /* Fields */
    public static final String type = "list";

    /* Constructor */
    public ListCommand() {
        super();
    }

    /* Methods */
    /**
     * the ListCommand operates
     * @param tasks the TaskList that the ListCommand shows
     */
    @Override
    public void op(TaskList tasks) {}

    /**
     * @return the type of the Command
     */
    public String getType() {
        return type;
    }
}
