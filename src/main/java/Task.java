import java.time.LocalDate;

public abstract class Task {

    protected final String task;
    protected boolean done;

    protected Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void doTask() {
        this.done = true;
    }

    public void undoTask() {
        this.done = false;
    }

    public abstract boolean isOnDate(LocalDate date);

    // returns "done | task description", can be added with task type for subtypes
    public String toSaveFormat() {
        return (this.done ? 1 : 0) + " | " + this.task;
    }

    @Override
    public String toString() {
        String mark = done ? "X" : " ";
        return String.format("[%s] %s", mark, this.task);
    }

}
