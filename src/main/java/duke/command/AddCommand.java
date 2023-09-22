package duke.command;

import duke.exception.NoFileException;
import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Adds a task to the list.
 */
public class AddCommand extends Command {

    private final Task task;
    /**
     * Constructor for an AddCommand.
     * @param task the task to be added to the list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the current add command.
     * @param taskList current list of tasks
     * @param ui instance of user interface
     * @param storage instance of storage to read and write files
     * @throws NoFileException Exception thrown if there is no file found when rewriting
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws NoFileException {
        String s = taskList.addTask(this.task, ui);
        storage.rewriteFile(taskList);
        return s;
    }

    /**
     * Checks if the current task is an exit task.
     * @return false since task is not an exit task
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
