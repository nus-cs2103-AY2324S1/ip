package duke.command;

import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The DeleteCommand class represents a command to delete a task from the task list.
 * It extends the Command class and is responsible for handling the execution of the command.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * Constructs a DeleteCommand with the specified user input.
     *
     * @param input The user input containing the task index to delete.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the "delete" command by deleting a task from the task list.
     *
     * @param tasks   The task list from which to delete the task.
     * @param ui      The user interface for displaying the deletion result.
     * @param storage The storage for saving the updated task list (not used in this command).
     * @return A string message indicating the task that has been deleted.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int taskIndex = Parser.extractTaskIndex(this.input);
        Task deletedTask = tasks.deleteTask(taskIndex);
        storage.saveTasksToFile(tasks.getAllTasks());
        return ui.showTaskDeleted(deletedTask, tasks.getTotalTasks());
    }
}
