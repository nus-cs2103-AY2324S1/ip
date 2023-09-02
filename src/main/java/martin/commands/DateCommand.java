package martin.commands;

import martin.Ui;
import martin.exceptions.InvalidDateFormatException;
import martin.exceptions.MartinException;
import martin.task.Deadline;
import martin.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class DateCommand implements Command {

    private Ui ui;
    private String dateStr;
    private ArrayList<Task> tasks;

    public DateCommand(String dateStr, ArrayList<Task> tasks) {
        this.ui = new Ui();
        this.dateStr = dateStr;
        this.tasks = tasks;
    }
    /**
    * Prints the tasks that are scheduled on a specific date.
    */
    @Override
    public void execute() throws MartinException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate date;
    
        try {
            date = LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("     Invalid date format. Please use the format 'd/M/yyyy'.");
        }

        ui.showLine();
        System.out.println("     Tasks on " + date.format(DateTimeFormatter.ofPattern("M d yyyy")) + ":");
        int count = 0;
        boolean hasTasks = false;

        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.getBy().toLocalDate().equals(date)) {
                    System.out.println("     " + (count + 1) + ". " + task);
                    hasTasks = true;
                }
            }
            count++;
        }

        if (!hasTasks) {
            System.out.println("     No tasks on this date.");
        }

        ui.showLine();
    }
}
