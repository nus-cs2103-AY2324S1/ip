package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts program to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private String[] taskNumbers;
    /**
     * Constructor for the class UnmarkCommand.
     * @param taskNumbers Indexes of the tasks to be marked as not done.
     */
    public UnmarkCommand(String[] taskNumbers) {
        this.taskNumbers = taskNumbers;
    }
    /**
     * Marks the specified task as not done in tasks,
     * shows appropriate response to user and updates tasks in storage.
     * @param tasks List of task stored by the program.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            TaskList unmarkList = tasks.markUndone(taskNumbers);
            storage.saveList(tasks);
            return ui.markUndoneResponse(unmarkList);
        } catch (ChatException e) {
            return ui.showLoadingError(e);
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
