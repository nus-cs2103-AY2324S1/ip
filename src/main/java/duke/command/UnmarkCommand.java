package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Command to set a Task to not done.
 */
public class UnmarkCommand extends NumberedChoiceCommand implements Command {
    private static final String commandString = "unmark";
    private final String arguments;

    /**
     * Constructor for Unmark command
     *
     * @param arguments arguments to Unmark command
     */
    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * If program should exit after command execution.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Mark a task as not done.
     *
     * @param taskList the current TaskList
     * @param ui       the UI tied to the program
     * @param storage  the Storage tied to the program
     * @throws DukeException if unable to mark a task as not done
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        validate(taskList);
        int choice = Integer.parseInt(arguments) - 1;
        taskList.get(choice).markAsNotDone();
        UI.sendMessage("OK, I've marked this task as not done yet:\n  " + taskList.get(choice));
        storage.updateFile(taskList);
    }

    /**
     * Validate the arguments to this command
     * They must be,
     * 1. Numeric
     * 2. Valid choice for the current TaskList
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
