package bert.commands;

import bert.tasks.Deadline;

import java.time.LocalDate;

/**
 * Represents a command that adds a deadline task.
 */
public class AddDeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";

    /**
     * Constructs an AddDeadlineCommand instance containing the deadline task to be added.
     *
     * @param description The description of the deadline task.
     * @param deadline The deadline of the deadline task.
     */
    public AddDeadlineCommand(String description, LocalDate deadline) {
        super(new Deadline(description, deadline));
    }
}
