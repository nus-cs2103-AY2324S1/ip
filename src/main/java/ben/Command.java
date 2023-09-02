package ben;

import java.time.format.DateTimeParseException;

public abstract class Command {
    public abstract boolean isExit();

    public abstract void execute(TaskList tasks, Ui ui) throws InvalidCommandException,
            EmptyDescriptionException, DateTimeParseException;
}
