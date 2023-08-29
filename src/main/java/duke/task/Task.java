package duke.task;

import duke.DukeException;
import java.time.LocalDateTime;

public abstract class Task {
    private boolean finish;
    private String task;

    public Task (String task) throws DukeException {
        if (task.isEmpty() || task.equals(" ")) {
            throw new DukeException("Description is EMPTY!!!\n");
        }
        this.finish = false;
        this.task = task;
    }

    public Task (String task, boolean finish) throws DukeException {
        this.finish = finish;
        this.task = task;
    }

    public boolean done() {
        return this.finish;
    }

    public void markDone() {
        this.finish = true;
    }

    public void markUndone() {
        this.finish = false;
    }

    public abstract LocalDateTime getDate();

    @Override
    public String toString() {
        if (this.finish) {
            return "[x] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
