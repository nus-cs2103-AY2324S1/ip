package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts program to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;
    /**
     * Constructor for the class UnmarkCommand.
     * @param taskNumber Index of the task to be marked as not done.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    /**
     * Marks the specified task as not done in tasks,
     * shows appropriate response to user and updates tasks in storage.
     * @param tasks List of task stored by the program.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markUndone(taskNumber);
            ui.markUndoneResponse(tasks.getTask(taskNumber));
            storage.saveList(tasks);
        } catch (ChatException e) {
            ui.showLoadingError(e);
        }
    };
    /**
     * Checks if command will end program.
     * @return False.
     */
    public boolean isExit() {
        return false;
    };
}
