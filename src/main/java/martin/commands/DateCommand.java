package martin.commands;

import martin.exceptions.InvalidDateFormatException;
import martin.exceptions.MartinException;
import martin.task.Deadline;
import martin.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a command that shows Tasks on the specified date.
 */
public class DateCommand implements Command {

    private String command;
    private ArrayList<Task> tasks;

    public DateCommand(String command, ArrayList<Task> tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    /**
     * Executes the command, returning a formatted string of tasks for the given date.
     * 
     * @return A string representation of tasks for the given date.
     * @throws MartinException If there's an error executing the command.
     */
    @Override
    public String execute() throws MartinException {
        LocalDate date = parseDate(command);
        return formatTasksForDate(date);
    }

    /**
     * Parses the date from the given command.
     *
     * @param command The input command string.
     * @return LocalDate The parsed date.
     * @throws InvalidDateFormatException When the date format is incorrect.
     */
    private LocalDate parseDate(String command) throws InvalidDateFormatException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        String[] parts = command.split(" ", 2);  // Splits into two parts

        if (parts.length < 2) {
            throw new InvalidDateFormatException("Invalid date format. The command is missing a date.");
        }

        String dateStr = parts[1].trim();
        
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format. Please use the format 'd/M/yyyy'.");
        }
    }

    /**
     * Formats the tasks for the given date.
     * 
     * @param date The date for which tasks should be formatted.
     * @return A string representation of tasks for the given date.
     */
    private String formatTasksForDate(LocalDate date) {
        StringBuilder response = new StringBuilder();
        response.append("Tasks on ").append(date.format(DateTimeFormatter.ofPattern("M d yyyy"))).append(":\n");

        // Get tasks for the date
        String tasksOnDate = tasks.stream()
            .filter(task -> task instanceof Deadline && ((Deadline) task).getBy().toLocalDate().equals(date))
            .map(task -> task.toString())
            .collect(Collectors.joining("\n"));

        if (tasksOnDate.isEmpty()) {
            response.append("No tasks on this date.\n");
        } else {
            response.append(tasksOnDate);
        }

        return response.toString();
    }
}
