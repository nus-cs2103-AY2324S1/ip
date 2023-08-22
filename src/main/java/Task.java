public abstract class Task {
    protected String title;
    protected Boolean done;

    public Task(String title, boolean done) {
        this.title = title;
        this.done = done;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    @Override
    public String toString() {
        if (this.done == true) {
            return "[X] "  + this.title;
        }
        return "[ ] " + this.title;
    }
}
