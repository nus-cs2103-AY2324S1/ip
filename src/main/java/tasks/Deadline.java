package tasks;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public LocalDate date;

    public Deadline(String name, String by) {
        super(name);
        this.date = LocalDate.parse(by, DateTimeFormatter.ofPattern("d MMM yyyy"));

    }


    /**
     * Returns a string that represents the Deadline
     *
     * @return string with details of the Deadline
     */
    @Override
    public String toString() {
        String dateString = this.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return "[D]" + super.toString() + " (by: " + dateString + ")";
    }

    /**
     * Returns a string that represents the Deadline to be stored in txt file
     *
     * @return a formatted string with details of the Deadline
     */
    @Override
    public String taskToStringStore(Task task) {
        String dateString = this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "D-" + super.taskToStringStore(task) + dateString;
    }
}
