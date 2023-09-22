package duke.command;

import duke.Ui;
import duke.Storage;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidDateTimeFormatException;
import duke.task.*;

/**
 * Adds a deadline task to the taskList
 */
public class DeadlineCommand extends Command {
    protected final static String regex = "^deadline\\s([\\w\\s]*)\\s\\/by\\s([\\w\\s\\:\\-]*)$";
    public DeadlineCommand(String response) {
        super(response, regex);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) throws InvalidCommandException {
        try {
            if (!matcher.find() || matcher.groupCount() != 2) {
                throw new InvalidCommandException(
                        "Invalid use of deadline. Usage: deadline <task description> /by <date & time>"
                );
            }
            String description = matcher.group(1);
            String by = matcher.group(2);
            Task task = new Deadlines(description, by);
            taskList.addTask(task);
            return "Got it. I've added this task:\n" +
                    task + "\n" +
                    String.format("Now you have %d tasks in your list", taskList.getSize());
        } catch (InvalidCommandException | InvalidDateTimeFormatException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}
