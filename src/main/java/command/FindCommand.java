package command;

import duke.Ui;
import storage.FileHandler;
import storage.TaskList;


/**
 * A find command to query task with a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs an `FindCommand` object.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @throws IllegalArgumentException If the provided keyword is null.
     */
    public FindCommand(String keyword) {
        assert keyword != null : "keyword must not be null";
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     *
     * @param t  The list of the task to match.
     * @param ui The user interface.
     * @param f  The file handler (not used in this command).
     *
     * @return   A string representation of the found task.
     */
    @Override
    public String execute(TaskList t, Ui ui, FileHandler f) {
        TaskList taskList = t.findKeyword(keyword);
        if (taskList.isEmpty()) {
            return "There is no task matched with the given keyword!";
        } else {
            return "Here are the tasks that matches your keyword.\n" + taskList.toTaskStr();
        }
    }
}
