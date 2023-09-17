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

    private String command;
    private ArrayList<Task> tasks;

    public EventCommand(String command, ArrayList<Task> tasks) {
        this.command = command;
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
        if (command.length() <= 5) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        String[] parts = command.substring(6).split(" /from ");
        String[] timeParts = parts[1].split(" /to ");

        if (parts.length < 2 || parts[0].trim().isEmpty() || timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of an event or its time cannot be empty.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            LocalDateTime from = LocalDateTime.parse(timeParts[0].trim(), formatter);
            LocalDateTime to = LocalDateTime.parse(timeParts[1].trim(), formatter);

            tasks.add(new Event(parts[0].trim(), from, to));

            return "Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\n     Now you have " + tasks.size() + " tasks in the list.";
        } catch (DateTimeParseException e) {
            throw new MartinException("☹ OOPS!!! The time of the event is in an invalid format. Please use the format d/M/yyyy HHmm.");
        }
    }
}
