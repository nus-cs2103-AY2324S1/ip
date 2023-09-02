package duke.commands.TaskCommands;

import java.time.LocalDate;
import java.util.regex.Matcher;

import duke.TaskListStorage;
import duke.commands.TaskCommand;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;
import duke.tasks.Deadline;

/**
 * Represents a command that adds a deadline task to the task list.
 */
public class DeadlineCommand extends TaskCommand {

    private LocalDate deadline;

    /**
     * Creates a DeadlineCommand object.
     * 
     * @param matcher The matcher object used to extract the description and deadline.
     * @throws MissingDescriptionException If the description is missing.
     * @throws IncorrectCommandFormatException If the command is in the wrong format.
     * @throws InvalidTimeFormatException If the time provided is invalid.
     */
    public DeadlineCommand(Matcher matcher) throws MissingDescriptionException, IncorrectCommandFormatException, InvalidTimeFormatException {
        super(matcher);
        this.deadline = extractDeadline();
    }

    @Override
    public void execute(TaskListStorage taskListStorage) throws MissingDescriptionException, IncorrectCommandFormatException {
        // Actual logic for handling the "deadline" command
        taskListStorage.addDeadline(new Deadline(this.description, deadline));
    }

    /**
     * Extracts the deadline from the user input.
     * 
     * @return The deadline.
     * @throws IncorrectCommandFormatException If the command is in the wrong format.
     * @throws InvalidTimeFormatException If the time provided is invalid.
     */
    public LocalDate extractDeadline() throws IncorrectCommandFormatException, InvalidTimeFormatException {
        if (this.matcher.matches()) {
            String byTime = matcher.group("byTime");
            if (byTime == null || byTime.trim().isEmpty()) {
                throw new IncorrectCommandFormatException("deadline <description> /by <date>");
            }
            try {
                return LocalDate.parse(byTime.trim());
            } catch (java.time.format.DateTimeParseException e) {
                throw new InvalidTimeFormatException("deadline <description> /by <date>");
            }
        }
        return null;
    }
}