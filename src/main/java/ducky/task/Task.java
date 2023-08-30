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
