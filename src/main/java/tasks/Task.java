package tasks;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Task {
    protected String text;
    protected boolean isDone;
    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d-MMMM-yyyy h:mma", Locale.ENGLISH);


    public Task(String text) {
        this.text = text;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : "");
    }

    public String getTaskDescription() {
        return this.text;
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public String toSaveFormat() {
        return " | " + (this.isDone ? 1 : 0) + " | " + this.text;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.text;
    }

}
