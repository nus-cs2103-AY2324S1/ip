/**
 * A task to do in the chatbot.
 */
public class Task {
    /** Name of the task */
    private String name;

    /** The completion status of the task */
    private boolean doneness;

    public Task(String name) {
        this.name = name;
        doneness = false;
    }

    public String toString() {
        return String.format("[%s] %s", isDone() ? "X" : " ", name);
    }

    public boolean isDone() {
        return doneness;
    }

    public void mark() {
        doneness = true;
    }

    public void unmark() {
        doneness = false;
    }
}
