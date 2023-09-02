package crusader.task;

/**
 * A task to do in the bot.
 */
public abstract class Task {
    /** Name of the task */
    private final String name;

    /** The completion status of the task */
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
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
        return isDone;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }
}
