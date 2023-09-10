package duck.command;

import java.util.ArrayList;

import duck.DuckException;
import duck.Storage;
import duck.task.Task;
import duck.task.TaskList;
import duck.ui.Ui;

/**
 * Represents an executable command which finds matching tasks.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a new find command.
     * 
     * @param keyword Keyword to be searched for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DuckException {
        ArrayList<Task> matchingTasks = tasks.find(keyword);

        if (matchingTasks.size() == 0) {
            return "There are no matching tasks in your list.";
        }

        String reply = "Here are the matching tasks in your list: ";
        for (int i = 0; i < matchingTasks.size(); i++) {
            reply += "\n" + (i + 1) + ". " + matchingTasks.get(i);
        }
        return reply;
    }
}
