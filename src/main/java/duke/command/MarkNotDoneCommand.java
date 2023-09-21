package duke.command;

import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The MarkNotDoneCommand class represents a command to mark a task as not done in the Duke application.
 * It extends the Command class and is responsible for handling the execution of the command.
 */
public class MarkNotDoneCommand extends Command {
    private String input;

    /**
     * Constructs a MarkNotDoneCommand object with the provided input.
     *
     * @param input The input string containing the command and task index.
     */
    public MarkNotDoneCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the "mark as not done" command by marking the specified task as not done in the task list.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the updated task list to a file.
     * @return A message indicating the successful execution of the command.
     * @throws DukeException If there is an error executing the command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert this.input != null : "input cannot be null";
        int taskIndex = Parser.extractTaskIndex(this.input);

        // Check if the task is already unmarked
        if (!tasks.isTaskDone(taskIndex)) {
            return "Relax, this task is already marked as not done! It isn't going to run away from you";
        }

        tasks.markAsNotDone(taskIndex);
        storage.saveTasksToFile(tasks.getAllTasks());
        return ui.showTaskMarkedAsNotDone(tasks.getTask(taskIndex));
    }
}
