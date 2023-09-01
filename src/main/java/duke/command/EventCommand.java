package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import duke.DukeException;
import duke.Storage;
import duke.task.Event;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Command to create an Event Task.
 */
public class EventCommand extends NonemptyArgumentCommand implements Command {

    private static final String commandString = "event";
    private final String arguments;

    /**
     * Constructor for an EventCommand
     *
     * @param arguments arguments for EventCommand
     */
    public EventCommand(String arguments) {
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
     * Validate arguments for this command.
     * They must,
     * 1. Be in the format [description] /from YYYY-MM-DD /to YYYY-MM-DD
     *
     * @param arguments arguments to validate
     * @throws DukeException if arguments are invalid
     */
    @Override
    protected void validate(String arguments) throws DukeException {
        super.validate(arguments);
        String[] userArgs = arguments.split("/from ");
        if (userArgs.length != 2) {
            throw new DukeException("Missing Argument for command: "
                    + commandString
                    + ", should include /from YYYY-MM-DD /to YYYY-MM-DD");
        }
        String desc = userArgs[0];
        String[] subcommandArgs = userArgs[1].split("/to ");
        if (subcommandArgs.length != 2) {
            throw new DukeException("Missing Argument for command: "
                    + commandString
                    + ", should include /from YYYY-MM-DD /to YYYY-MM-DD");
        }
        if (Objects.equals(subcommandArgs[0], "") || Objects.equals(subcommandArgs[1], "")) {
            throw new DukeException("Missing Argument for command: "
                    + commandString
                    + ", should include /from YYYY-MM-DD /to YYYY-MM-DD");
        }
        try {
            LocalDate date = LocalDate.parse(subcommandArgs[0].trim());
            date = LocalDate.parse(subcommandArgs[1].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date Format for command: "
                    + commandString
                    + "should be /from YYYY-MM-DD /to YYYY-MM-DD");
        }
    }

    /**
     * Create an Event Task.
     *
     * @param taskList the current TaskList
     * @param ui       the UI tied to the program
     * @param storage  the Storage tied to the program
     * @throws DukeException if unable to create event
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        validate(this.arguments);
        String[] userArgs = arguments.split("/from |/to ");
        LocalDate from = LocalDate.parse(userArgs[1].trim());
        LocalDate to = LocalDate.parse(userArgs[2].trim());
        taskList.add(new Event(userArgs[0].trim(), from, to));
        UI.sendMessage("Got it. I've added this task:\n  "
                + taskList.get(taskList.size() - 1)
                + String.format("\nNow you have %d tasks in the list.", taskList.size()));
        storage.updateFile(taskList);
    }

    @Override
    public String toString() {
        return commandString;
    }
}
