/**
 * A task to do in the chatbot.
 */
public abstract class Task {
    /** Name of the task */
    private final String name;

    /** The completion status of the task */
    private boolean doneness;

    public Task(String name) {
        this.name = name;
        doneness = false;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone() ? "X" : " ", name);
    }

    public abstract String toFormat();

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
