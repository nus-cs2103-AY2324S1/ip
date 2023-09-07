package chatterchicken.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {

    private final String taskDescription;
    private final String name;
    private boolean isDone;

    public Task(String taskDescription, String name) {
        this.taskDescription = taskDescription;
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    protected String getTaskDescription() {
        return this.taskDescription;
    }

    public abstract String getInput();

    public abstract String getTaskForPrinting();

    public String getTaskForSaving() {
        String done = " ";
        if (isDone) {
            done = "X";
        }
        return done + getInput();
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected String checkDone() {
        return isDone ? "X" : " ";
    }

    public String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
}
