package command;

import duke.Storage;
import duke.Ui;

import task.TaskList;
import task.Todo;

/**
 * Adds a todo, which has a description, to the todo list
 */
public class TodoCommand extends Command {

    /** Description of the task */
    protected String description;
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_SUCCESS = " Got it. I've added this task:\n";

    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds the todo to the TaskList, and saves the current TaskList to the specified
     * Storage file
     *
     * @param tasks TaskList which contains an ArrayList of tasks
     * @param ui Text Ui that the user interacts with
     * @param storage File path where the tasks are stored
     */

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Todo todo = new Todo(this.description);
        tasks.addTask(todo);
        storage.writeToFile(tasks.getList());
        ui.showMessage(MESSAGE_SUCCESS + "     " + todo
                + "\n Now you have " + tasks.getSize() + " tasks in the list");
    }
}
