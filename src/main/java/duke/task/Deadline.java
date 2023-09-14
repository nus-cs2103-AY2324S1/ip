package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task with deadline
 *
 * @author Lian Zhi Xuan
 */
public class Deadline extends Task {

    private String date;

    public Deadline(String task, LocalDate date) {
        super(task);
        this.date = date.toString();
    }

    @Override
    public String getStatus() {
        return isDone() ? "[D][X]" : "[D][ ]";
    }

    @Override
    public String getTaskName() {
        return super.getTaskName() + " (by: " + date + ")";
    }


    @Override
    public String getType() {
        return "Task.Deadline";
    }

    /**
     * Show date of task
     *
     * @return date of task in LocalDate
     */
    public LocalDate getDate() {
        return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline temp = (Deadline) o;
            return this.getDate().equals(temp.getDate()) && getTaskName().equals(temp.getTaskName()) && isDone() == temp.isDone();
        }
        return false;
    }
}
