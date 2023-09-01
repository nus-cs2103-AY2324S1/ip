package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Command to delete a Task.
 */
public class DeleteCommand extends NumberedChoiceCommand implements Command {
    private static final String commandString = "mark";
    private final String arguments;

    /**
     * Constructor for the DeleteCommand
     *
     * @param arguments arguments for DeleteCommand
     */
    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Deletes a specified task.
     *
     * @param taskList the current TaskList
     * @param ui       the UI tied to the program
     * @param storage  the Storage tied to the program
     * @throws DukeException when unable to delete
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        validate(taskList);
        int choice = Integer.parseInt(arguments) - 1;
        if (ui != null) {
            ui.sendMessage("Noted. I've removed this task:\n  "
                    + taskList.get(choice)
                    + String.format("\nNow you have %d tasks in the list.", taskList.size() - 1));
        }
        taskList.remove(choice);
        storage.updateFile(taskList, ui);
    }

    /**
     * Validate arguments to this command.
     * They must be
     * 1. Numeric
     * 2. A valid choice given current tasks
     *
     * @param taskList the current TaskList
     * @throws DukeException if arguments are invalid
     */
    private void validate(TaskList taskList) throws DukeException {
        super.validate(this.arguments);
        int choice = Integer.parseInt(arguments) - 1;
        if (choice < 0 || choice >= taskList.size()) {
            throw new DukeException("Argument Provided out of range: " + (choice + 1));
        }
    }

    @Override
    public String toString() {
        return commandString;
    }
}
