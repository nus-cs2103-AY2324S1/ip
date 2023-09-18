package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Command to mark a Task as done.
 */
public class MarkCommand extends NumberedChoiceCommand implements Command {

    private static final String commandString = "mark";
    private final String arguments;

    /**
     * Constructor for MarkCommand.
     *
     * @param arguments arguments for MarkCommand.
     */
    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Markd a Task as done.
     *
     * @param taskList the current TaskList.
     * @param ui       the UI tied to the program.
     * @param storage  the Storage tied to the program.
     * @throws DukeException if unable to mark task as done.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {

        // Execute default statements
        Command.super.execute(taskList, ui, storage);

        validate(taskList);

        int choice = Integer.parseInt(arguments) - 1;

        assert choice >= 0 && choice < taskList.size();

        taskList.get(choice).markAsDone();
        if (ui != null) {
            ui.sendMessage("Nice! I've marked this task as done:\n  " + taskList.get(choice));
        }
        storage.updateFile(taskList, ui);
    }

    /**
     * Validates arguments to this command.
     * They must be,
     * 1. Numeric.
     * 2. Valid Choice for the current TaskList.
     *
     * @param taskList The current TaskList.
     * @throws DukeException if arguments are invalid.
     */
    private void validate(TaskList taskList) throws DukeException {

        // Validate Inherited Rules
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
