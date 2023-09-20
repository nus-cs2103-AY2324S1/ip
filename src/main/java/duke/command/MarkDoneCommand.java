package duke.command;

import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The MarkDoneCommand class represents a command to mark a task as done in the Duke application.
 * It extends the Command class and is responsible for handling the execution of the command.
 */
public class MarkDoneCommand extends Command {
    private String input;

    /**
     * Constructs a MarkDoneCommand object with the provided input.
     *
     * @param input The input string containing the command and task index.
     */
    public MarkDoneCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the "mark as done" command by marking the specified task as done in the task list.
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

        // Check if the task is already marked as done
        if (tasks.isTaskDone(taskIndex)) {
            return "Relax, you already did this task. It ain't going anywhere!";
        }

        tasks.markAsDone(taskIndex);
        storage.saveTasksToFile(tasks.getAllTasks());
        return ui.showTaskMarkedAsDone(tasks.getTask(taskIndex));
    }
}
