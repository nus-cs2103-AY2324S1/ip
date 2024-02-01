package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidDateException;
import duke.task.Event;

/**
 * The EventCommand class represents a command to add a Event task to the task list in the Duke application.
 * It parses the user input and handles exceptions related to invalid input or date formatting.
 */
public class EventCommand extends Command {

    /**
     * Constructs a new EventCommand object with the specified full command string.
     *
     * @param fullCommand The full command string as entered by the user.
     */
    public EventCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Executes the Event command, adding a new Event task to the task list.
     *
     * @param tasks   The task list to which the Event task will be added.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage object for reading from or writing to a data file.
     * @throws InvalidArgumentException If the command is missing required arguments.
     * @throws InvalidDateException      If the date provided is in an invalid format.
     */
    @Override
    public String execute(TaskList tasks , Ui ui, Storage storage)
            throws InvalidArgumentException, InvalidDateException {
        String[] words = this.fullCommand.split(" ", 2);
        super.validateArguments(words, "event");

        String[] splitCommand = words[1].split("/", 2);
        super.validateArguments(splitCommand, "event");

        String eventDescription = splitCommand[0];
        String[] splitDeadline = splitCommand[1].split("/", 2);
        super.validateArguments(splitDeadline, "event");
        super.validateArguments(splitDeadline[0].split(" ", 2), "event");
        super.validateArguments(splitDeadline[1].split(" ", 2), "event");

        try {
            LocalDateTime from = LocalDateTime.parse(splitDeadline[0].split(" ", 2)[1]
                    .strip(), Storage.DATE_TIME_INPUT_FORMATTER);
            LocalDateTime to = LocalDateTime.parse(splitDeadline[1].split(" ", 2)[1]
                    .strip(), Storage.DATE_TIME_INPUT_FORMATTER);
            Event t = new Event(eventDescription, from, to);
            return tasks.addTask(t, storage);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }
}
