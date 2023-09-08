package command;

import storage.FileHandler;
import storage.TaskList;

import duke.Ui;

/**
 * A find command to query task with a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs an `FindCommand` object.
     */
    public FindCommand(String keyword) {
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
            return "Here are the tasks that matches your keyword." + taskList.toTaskStr();
        }
    }

    /**
     * Checks whether the command is an exit command.
     *
     * @return `false` because this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
