package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Command to list Tasks.
 */
public class ListCommand implements Command {

    private static final String commandString = "list";
    private final String argument;

    /**
     * Constructor for ListCommand.
     *
     * @param argument arguments for ListCommand
     */
    public ListCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Validate arguments to this command.
     * They must have one of the following formats,
     * <ul>
     *     <li>list</li>
     *     <li>list now</li>
     *     <li>list YYYY-MM-DD</li>
     * </ul>
     *
     * @param arguments arguments to validate
     * @throws DukeException if arguments are invalid
     */
    private void validate(String arguments) throws DukeException {
        if (arguments != null) {
            // Non null argument, check format
            if (arguments.equals("now")) {
                return;
            }

            // User Specified Date, check date
            try {
                LocalDate date = LocalDate.parse(arguments);
            } catch (DateTimeParseException e) {
                throw new DukeException(
                        "Invalid Date Format Provided, expected either:\n\tlist\n\tlist now\n\tlist YYYY-MM-DD");
            }
        }
    }

    /**
     * List tasks depending on arguments.
     * <ul>
     *     <li>list: list all tasks</li>
     *     <li>list now: list all tasks ending within 1 week from now</li>
     *     <li>list YYYY-MM-DD: list all task ending before specified date</li>
     * </ul>
     *
     * @param taskList the current TaskList
     * @param ui       the UI tied to the program
     * @param storage  the Storage tied to the program
     * @throws DukeException if unable to list tasks
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        validate(this.argument);
        LocalDate date;
        if (this.argument != null) {
            if (this.argument.equals("now")) {
                date = LocalDate.now().plusWeeks(1L);
            } else {
                date = LocalDate.parse(this.argument);
            }
        } else {
            date = LocalDate.MAX;
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).isBefore(date)) {
                output.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
        }
        if (output.length() == 0) {
            ui.sendMessage("No Items in List");
        } else {
            ui.sendMessage(output.toString());
        }
    }

    @Override
    public String toString() {
        return commandString;
    }
}
