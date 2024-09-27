package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts program to mark a task as done.
 */
public class MarkCommand extends Command {
    private String[] taskNumbers;

    /**
     * Constructor for the class MarkCommand.
     * @param taskNumbers Indexes of the tasks to be marked as done.
     */
    public MarkCommand(String[] taskNumbers) {
        this.taskNumbers = taskNumbers;
    }
    /**
     * Marks the specified tasks as done in tasks,
     * shows appropriate response to user and updates tasks in storage.
     * @param tasks List of task stored by the program.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            TaskList markList = tasks.markDone(taskNumbers);
            storage.saveList(tasks);
            return ui.markDoneResponse(markList);
        } catch (ChatException e) {
            return ui.showLoadingError(e);
        }
    };
}
