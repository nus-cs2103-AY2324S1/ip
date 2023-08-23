public class Task {


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
}
