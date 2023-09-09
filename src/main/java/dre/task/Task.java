package dre.task;

import java.time.LocalDate;

public class Task {
    private String description;
    private boolean done;

    public Task() {
        description = "default";
        done = false;
    }
    public Task(String newTask){
        description = newTask;
        done = false;
    }

    public boolean isDone() {
        return done;
    }

    public void done() {
        done = true;
    }

    public void undo() {
        done = false;
    }

    public String getStatusIcon() {
        return (done ? "X" : " ");
    }

    public String fileSaveFormat() {
        return getStatusIcon() + "|" + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}