package ducky.task;

public abstract class Task {


    private final String description;
    private boolean completed;

    public Task(String desc) {
        this.description = desc;
        this.completed = false;
    }

    public void complete() {
        this.completed = true;
    }

    public void incomplete() {
        this.completed = false;
    }

    /**
     * Returns whether task description contains the given string as a substring.
     * @param s String to check in description.
     * @return True if description contains specified string.
     */
    public boolean containsString(String s) {
        return this.description.contains(s);
    }

    @Override
    public String toString() {
        if (this.completed) {
            return String.format("[X] %s", this.description);
        }
        return String.format("[ ] %s", this.description);
    }

    public String getSaveFormat() {
        return String.format(
                "%d | %s",
                this.completed ? 1 : 0,
                this.description
        );
    }
}
