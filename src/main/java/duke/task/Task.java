package duke.task;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Task {

    private String task;
    protected boolean isDone;


    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + task;
    }

    public Task completeTask() {
        this.isDone = true;
        return this;
    }

    public Task undoTask() {
        this.isDone = false;
        return this;
    }

    public String getTask() {
        return this.task;
    }

    public boolean match(String search) {
        return this.task.contains(search);
    }

    public abstract void writeToFile(FileWriter fw) throws IOException;
}
