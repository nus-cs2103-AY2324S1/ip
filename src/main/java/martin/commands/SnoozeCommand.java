package martin.commands;

import martin.exceptions.MartinException;
import martin.task.Task;
import java.time.Duration;
import java.util.ArrayList;

/**
 * Represents a command that handles the snoozing of tasks in the task list.
 */
public class SnoozeCommand implements Command {

    private String input; 
    private ArrayList<Task> tasks;

    /**
     * Creates a SnoozeCommand.
     *
     * @param input The user command input.
     * @param tasks The list of tasks.
     */
    public SnoozeCommand(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * Snoozes a task by the specified duration.
     *
     * @return A confirmation message indicating that the task has been snoozed.
     * @throws MartinException If there's an error executing the command.
     */
    @Override
    public String execute() throws MartinException {
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new MartinException("☹ OOPS!!! Incorrect format for snooze command.");
        }

        int taskIndex;
        int durationMinutes;

        try {
            taskIndex = Integer.parseInt(parts[1]);
            durationMinutes = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new MartinException("☹ OOPS!!! Invalid task index or snooze duration.");
        }

        if (taskIndex <= 0 || taskIndex > tasks.size()) {
            throw new MartinException("☹ OOPS!!! The task index is out of range.");
        }

        Task task = tasks.get(taskIndex - 1); // Adjust for 0-based indexing

        try {
            task.snooze(Duration.ofMinutes(durationMinutes));
            return "Got it. I've snoozed the task:\n       " + task;
        } catch (UnsupportedOperationException e) {
            throw new MartinException("☹ OOPS!!! This task cannot be snoozed.");
        }
    }
}
