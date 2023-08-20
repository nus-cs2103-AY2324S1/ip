/**
 * Encapsulates a Task in the Chat bot.
 *
 * @author Rayson
 */
public class Task {

    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    public String getStatus() {
        return (this.done ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatus(), this.description);
    }

}
