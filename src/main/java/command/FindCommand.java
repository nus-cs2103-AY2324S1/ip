package command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a command to find a task with a query.
 */
public class FindCommand extends Command {
    private String query;

    /**
     * Constructs a FindCommand object.
     */
    public FindCommand(String fullCommand) {
        super(false);
        int split = fullCommand.indexOf(" ");
        this.query = fullCommand.substring(split + 1);
    }

    /**
     * Executes the find command, displaying the list of tasks matching query.
     *
     * @param taskList The task list to operate on.
     * @param storage  The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        TaskList queryList = taskList.findTask(this.query);
        String s = String.format("%s\n %d task(s) match your query, sexy!",
                queryList.getListInString(), queryList.getLength());
        return s;
    }
}
