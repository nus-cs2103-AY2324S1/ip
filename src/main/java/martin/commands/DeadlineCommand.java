package martin.commands;

import martin.exceptions.EmptyTaskDescriptionException;
import martin.exceptions.MartinException;
import martin.task.Deadline;
import martin.task.Task;

import java.util.ArrayList;

/**
 * Represents a command that handles the addition of Deadline tasks.
 */
public class DeadlineCommand implements Command {

    private String input;
    private ArrayList<Task> tasks;

    /**
     * Creates a Deadline Command.
     *
     * @param input The user command input.
     * @param tasks The list of tasks.
     */
    public DeadlineCommand(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @return A confirmation message indicating that the Deadline task has been added.
     * @throws EmptyTaskDescriptionException If the description of the deadline or its date is missing.
     * @throws MartinException If there's an error executing the command.
     */
    @Override
    public String execute() throws MartinException {
        if (input.length() <= 8) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] parts = input.substring(9).split(" /by ");
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a deadline or its date cannot be empty.");
        }

        tasks.add(new Deadline(parts[0], parts[1]));
        return "Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\n     Now you have " + tasks.size() + " tasks in the list.";
    }
}
