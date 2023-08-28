package pogo.commands;

import pogo.tasks.Deadline;
import pogo.tasks.Task;

/**
 * Represents a command to add a deadline task.
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    /** Message to show if the command format is invalid. */
    private static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline task to the task list. "
            + "Parameters: DESCRIPTION /by DEADLINE\n"
            + "Example: " + COMMAND_WORD
            + " Eat eggs /by 2023-09-18 2359";

    private final String description;
    private final String by;

    /**
     * Creates an AddDeadlineCommand to add a deadline task.
     * @param description Description of the deadline task.
     * @param by Deadline of the task.
     */
    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Creates a deadline task and adds it to the task list.
     * @return CommandResult containing a success message for the task.
     */
    @Override
    public CommandResult execute() {
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        return new CommandResult("Got it. I've added this task:\n" + deadline.getStatusMessage());
    }

}
