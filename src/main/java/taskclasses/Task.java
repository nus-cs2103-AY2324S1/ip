package main.java.taskclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public static Task createTaskFromData(String dataLine) {

        String[] parts = dataLine.split(" \\| ");
        String type = parts[0].trim();
        boolean isDone = parts[1].equals("1");
        String description = parts[2].trim();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        if (type.equals("T")) {
            return new Todo(description);
        } else if (type.equals("D")) {
            LocalDate by = LocalDate.parse(parts[3].trim(), inputFormatter);
            return new Deadline(description, isDone, by);
        } else if (type.equals("E")) {

            String[] dates = parts[3].split(" - ");

            String fromDateString = dates[0];
            String toDateString = dates[1];

            LocalDate from = LocalDate.parse(fromDateString, inputFormatter);
            LocalDate to = LocalDate.parse(toDateString, inputFormatter);
            return new Event(description, isDone, from, to);
        } else {
            return null; // Unknown task type
        }
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String formatToFile() {
        int status = isDone ? 1 : 0;
        return status + " | " + description;
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
