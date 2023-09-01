package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import duke.DukeException;
import duke.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Command to create a Deadline Task.
 */
public class DeadlineCommand extends NonemptyArgumentCommand implements Command {

    private static final String commandString = "deadline";
    private final String arguments;

    /**
     * Constructor for a DeadlineCommand
     *
     * @param arguments arguments for the DeadlineCommand
     */
    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Validate arguments to this command.
     * They must,
     * 1. Be in the format [description] /by YYYY-MM-DD
     *
     * @param arguments argument to validate
     * @throws DukeException is argument is not valid
     */
    @Override
    protected void validate(String arguments) throws DukeException {
        super.validate(arguments);
        String[] userArgs = arguments.split("/by ");
        if (userArgs.length != 2) {
            throw new DukeException("Missing Argument for command: "
                    + commandString
                    + ", should include /by YYYY-MM-DD");
        }
        if (Objects.equals(userArgs[1], "")) {
            throw new DukeException("Missing Argument for command: "
                    + commandString
                    + ", should include /by YYYY-MM-DD");
        }
        try {
            LocalDate date = LocalDate.parse(userArgs[1]);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date Format for command: "
                    + commandString
                    + ", should be /by YYYY-MM-DD");
        }

    }

    /**
     * Creates a Deadline task.
     *
     * @param taskList the current TaskList
     * @param ui       the UI tied to the program
     * @param storage  the Storage tied to the program
     * @throws DukeException if deadline task cannot be created
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        validate(this.arguments);
        String[] userArgs = arguments.split("/by ");
        LocalDate date = LocalDate.parse(userArgs[1]);
        taskList.add(new Deadline(userArgs[0], date));
        if (ui != null) {
            ui.sendMessage("Got it. I've added this task:\n  "
                    + taskList.get(taskList.size() - 1)
                    + String.format("\nNow you have %d tasks in the list.", taskList.size()));
        }
        storage.updateFile(taskList, ui);
    }

    @Override
    public String toString() {
        return commandString;
    }
}
