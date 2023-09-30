package veneto.command;

import veneto.task.TaskList;

public class ExitCommand extends Command {
    /* Fields */
    public static final String type = "exit";

    /* Constructor */
    public ExitCommand() {
        super();
    }

    /* Methods */
    /**
     * the ExitCommand operates
     * @param tasks the TaskList of Veneto
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
