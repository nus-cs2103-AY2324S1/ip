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
     * Constructor for Unmark command.
     *
     * @param arguments arguments to Unmark command.
     */
    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Marks a task as not done.
     *
     * @param taskList the current TaskList.
     * @param ui       the UI tied to the program.
     * @param storage  the Storage tied to the program.
     * @throws DukeException if unable to mark a task as not done.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {

        // Execute default statements
        Command.super.execute(taskList, ui, storage);

        validate(taskList);

        int choice = Integer.parseInt(arguments) - 1;

        assert choice >= 0 && choice < taskList.size();

        taskList.get(choice).markAsNotDone();

        if (ui != null) {
            ui.sendMessage("OK, I've marked this task as not done yet:\n  " + taskList.get(choice));
        }

        storage.updateFile(taskList, ui);
    }

    /**
     * Validates the arguments to this command.
     * They must be,
     * 1. Numeric.
     * 2. Valid choice for the current TaskList.
     *
     * @param taskList the current TaskList.
     * @throws DukeException if arguments are invalid.
     */
    private void validate(TaskList taskList) throws DukeException {

        // Validate inherited rules
        super.validate(this.arguments);

        // Ensure valid choice
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
