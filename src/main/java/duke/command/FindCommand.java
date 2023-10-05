package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Command Find that find a task from the list
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs an FindCommand to find a specific task.
     *
     * @param keyword A string describing the task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand and returns the message.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui that used as user interface.
     * @param storage The Storage that used to store, read and write data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList taskList = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).contains(keyword)) {
                taskList.add(tasks.getTask(i));
            }
        }
        return ui.showFind(taskList);
    }

    /**
     * Check if it is an ExitCommand
     *
     * @return a boolean that represent whether this is an ExitCommand or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
