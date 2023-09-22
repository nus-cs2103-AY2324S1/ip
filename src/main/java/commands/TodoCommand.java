package commands;

import data.Actions;
import duke.DukeException;
import tasks.Todo;
import ui.UI;

/**
 * Represents the "todo" command to add a todo task.
 *
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Initializes a TodoCommand with the given input string.
     *
     * @param description The input string provided by the user.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the TodoCommand by adding a "todo" task to the task list.
     * If the description is empty, it will throw an exception to prompt for a valid description.
     *
     * @param ui The UI instance.
     * @param actionList The list of tasks.
     * @throws DukeException To handle incorrect input format.
     */
    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException(" Provide the description. "
                    + "Format: todo task");
        }
        Todo task = new Todo(description);
        actionList.add(task);
        ui.lineSandwich(" Got it. I've added this task:\n  " + task + "\n Now you have "
                + actionList.size() + " tasks in the list.");
    }
}
