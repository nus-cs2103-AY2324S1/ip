import java.io.Serializable;

public class Task implements Serializable {

    private boolean isDone;
    private String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() throws DukeException{
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new DukeException("It's already done.");
        }
    }

    public void markUndone() throws DukeException {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new DukeException("This hasn't been done yet...");
        }
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + name : "[ ] " + name;
    }

}
