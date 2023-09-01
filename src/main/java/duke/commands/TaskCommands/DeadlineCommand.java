package duke.commands.TaskCommands;

import java.time.LocalDate;
import java.util.regex.Matcher;

import duke.TaskListStorage;
import duke.commands.TaskCommand;
import duke.exceptions.IncorrectCommandFormatException;
import duke.exceptions.InvalidTimeFormatException;
import duke.exceptions.MissingDescriptionException;
import duke.tasks.Deadline;

public class DeadlineCommand extends TaskCommand {

    LocalDate deadline;

    public DeadlineCommand(Matcher matcher) throws MissingDescriptionException, IncorrectCommandFormatException, InvalidTimeFormatException {
        super(matcher);
        this.deadline = extractDeadline();
    }

    @Override
    public void execute(TaskListStorage taskListStorage) throws MissingDescriptionException, IncorrectCommandFormatException {
        // Actual logic for handling the "deadline" command
        taskListStorage.addDeadline(new Deadline(this.description, deadline));
    }

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