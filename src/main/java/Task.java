public class Task {
    private String name;
    private Boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void toggleDone() {
        this.done = !this.done;
    }

    public boolean getDone() {
        return this.done;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        if (!this.done) {
            return "[ ] " + this.name;
        } else {
            return "[X] " + this.name;
        }
    }
}