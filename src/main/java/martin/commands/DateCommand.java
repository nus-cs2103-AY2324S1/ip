package martin.commands;

import martin.exceptions.InvalidDateFormatException;
import martin.exceptions.MartinException;
import martin.task.Deadline;
import martin.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DateCommand implements Command {

    private String dateStr;
    private ArrayList<Task> tasks;

    public DateCommand(String dateStr, ArrayList<Task> tasks) {
        this.dateStr = dateStr;
        this.tasks = tasks;
    }
    
    /**
    * Returns a String of the tasks that are scheduled on a specific date.
    * @return String A formatted string containing the tasks on the specified date.
    */
    @Override
    public String execute() throws MartinException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate date;
        StringBuilder response = new StringBuilder();
    
        try {
            date = LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format. Please use the format 'd/M/yyyy'.");
        }

        response.append("Tasks on ").append(date.format(DateTimeFormatter.ofPattern("M d yyyy"))).append(":\n");
        int count = 0;
        boolean hasTasks = false;

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.getBy().toLocalDate().equals(date)) {
                    response.append((count + 1)).append(". ").append(task).append("\n");
                    hasTasks = true;
                }
            }
            count++;
        }

        if (!hasTasks) {
            response.append("No tasks on this date.\n");
        }

        return response.toString();
    }
}
