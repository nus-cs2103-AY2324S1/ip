package duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {

    private boolean isDone;
    private String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() throws AlreadyMarkedException {
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new AlreadyMarkedException();
        }
    }

    public void markUndone() throws AlreadyUnmarkedException {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new AlreadyUnmarkedException();
        }
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + name : "[ ] " + name;
    }

    public abstract String getAddMessage();

}
