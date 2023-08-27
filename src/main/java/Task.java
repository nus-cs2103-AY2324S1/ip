public class Task {
    Boolean done;
    String name;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }
    public Boolean isDone() {
        return this.done;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    public String toString() {
        if (this.done) {
            return "[X] " + this.name;
        }

        return "[ ] " + this.name;
    }
}
