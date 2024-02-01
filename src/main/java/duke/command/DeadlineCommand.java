package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidDateException;
import duke.task.Deadline;

/**
 * The DeadlineCommand class represents a command to add a Deadline task to the task list in the Duke application.
 * It parses the user input and handles exceptions related to invalid input or date formatting.
 */
public class DeadlineCommand extends Command {

    /**
     * Constructs a new DeadlineCommand object with the specified full command string.
     *
     * @param fullCommand The full command string as entered by the user.
     */
    public DeadlineCommand(String fullCommand) {

        super(fullCommand);
    }
    /**
     * Executes the deadline command, adding a new deadline task to the task list.
     *
     * @param tasks   The task list to which the deadline task will be added.
     * @param ui      The user interface for displaying messages to the user.
     * @param storage The storage object for reading from or writing to a data file.
     * @throws InvalidArgumentException If the command is missing required arguments.
     * @throws InvalidDateException      If the date provided is in an invalid format.
     */
    @Override
    public String execute(TaskList tasks , Ui ui, Storage storage)
            throws InvalidArgumentException, InvalidDateException {
        String[] words = this.fullCommand.split(" ", 2);
        super.validateArguments(words, "deadline");

        String[] taskWithDeadline = words[1].split("/", 2);
        String deadlineDescription = taskWithDeadline[0];
        super.validateArguments(taskWithDeadline, "deadline");

        String[] splitTask = taskWithDeadline[1].split(" ", 2);
        super.validateArguments(splitTask, "deadline");

        try {
            LocalDateTime deadline = LocalDateTime.parse(splitTask[1], Storage.DATE_TIME_INPUT_FORMATTER);
            Deadline t = new Deadline(deadlineDescription, deadline);
            return tasks.addTask(t, storage);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }
}
