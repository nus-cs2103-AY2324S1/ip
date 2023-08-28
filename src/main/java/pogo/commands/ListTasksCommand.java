package pogo.commands;

/**
 * Lists all the tasks in the task list.
 */
public class ListTasksCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public ListTasksCommand() {
    }

    @Override
    public CommandResult execute() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i).getStatusMessage()).append("\n");
        }
        return new CommandResult(sb.toString());
    }
}
