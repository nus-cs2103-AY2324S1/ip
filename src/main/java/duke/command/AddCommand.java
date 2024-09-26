package duke.command;

import duke.Parser;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The AddCommand class represents a command for adding tasks in the Duke application.
 * It allows the user to add tasks such as todo, deadline, and event tasks.
 */
public class AddCommand extends Command {
    private String input;

    /**
     * Constructs an AddCommand with the specified input.
     *
     * @param input The user input containing task details.
     */
    public AddCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the "add" command, parsing the input to create a new task and adding it to the task list.
     *
     * @param tasks   The task list where the new task will be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks to a file.
     * @return A string message indicating that the task has been added successfully.
     * @throws DukeException If there is an error parsing the input or adding the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = Parser.parseTask(this.input);
        assert newTask != null : "New task cannot be null";
        tasks.addTask(newTask);
        storage.saveTasksToFile(tasks.getAllTasks());
        return ui.showTaskAdded(newTask, tasks.getTotalTasks());
    }
}
