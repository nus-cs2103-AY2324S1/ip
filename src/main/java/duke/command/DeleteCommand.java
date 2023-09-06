package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts the programme to delete a task that has been saved.
 */
public class DeleteCommand extends Command{
    private int taskNumber;

    /**
     * Constructor for the class DeleteCommand.
     * @param taskNumber Index of the task in the saved list to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    /**
     * Deletes the specified task from tasks,
     * shows appropriate response to user and updates tasks in storage.
     * @param tasks List of task stored by the programme.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try {
            ui.deleteTaskResponse(tasks.getTask(taskNumber), tasks);
            tasks.deleteTask(taskNumber);
            storage.saveList(tasks);
        } catch (ChatException e) {
            ui.showLoadingError(e);
        }
    }
    /**
     * Checks if command will end programme.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }
}
