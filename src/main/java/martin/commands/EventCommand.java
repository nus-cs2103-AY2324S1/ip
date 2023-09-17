package martin.commands;

import martin.exceptions.EmptyTaskDescriptionException;
import martin.exceptions.MartinException;
import martin.task.Event;
import martin.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a command that handles the addition of events to the task list.
 */
public class EventCommand implements Command {

    private String input;
    private ArrayList<Task> tasks;

    /**
     * Creates an Event Command.
     *
     * @param input The user command input.
     * @param tasks The list of tasks.
     */
    public EventCommand(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @return A confirmation message indicating that the event has been added.
     * @throws EmptyTaskDescriptionException If the event description or its time is empty.
     * @throws MartinException If there's any other error executing the command.
     */
    @Override
    public String execute() throws MartinException {
        if (input.length() <= 5) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        String[] parts = input.substring(6).split(" /from ");

        if (parts.length < 2) {
            throw new MartinException("☹ OOPS!!! The command is missing the time details. Please specify the event starting time using /from.");
        }

        String[] timeParts = parts[1].split(" /to ");

        if (timeParts.length < 2) {
            throw new MartinException("☹ OOPS!!! The command is missing the ending time. Please specify the event ending time using /to.");
        }

        if (parts[0].trim().isEmpty() || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of an event or its time cannot be empty.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime from = LocalDateTime.parse(timeParts[0].trim(), formatter);
            LocalDateTime to = LocalDateTime.parse(timeParts[1].trim(), formatter);

            if (from.isAfter(to) || from.isEqual(to)) {
                throw new MartinException("☹ OOPS!!! The start time of the event must be earlier than the end time.");
            }

            tasks.add(new Event(parts[0].trim(), from, to));

            return "Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\n     Now you have " + tasks.size() + " tasks in the list.";
        } catch (DateTimeParseException e) {
            throw new MartinException("☹ OOPS!!! The time of the event is in an invalid format. Please use the format d/M/yyyy HHmm.");
        }
    }
}
