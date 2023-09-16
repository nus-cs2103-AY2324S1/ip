package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkDoneCommand extends Command {
    private int taskNum;

    /**
     * Constructs a new MarkDoneCommand with the given task number.
     *
     * @param taskNum The index of the task to be marked as done.
     */
    public MarkDoneCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
    }

    /**
     * Executes the mark done command to mark a task as done and display it.
     *
     * @param taskList The list of tasks.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the task list.
     * @return A message indicating the task was successfully marked.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(taskNum);
        return ui.showDoneTask(taskList.getTask(taskNum));
    }
}
