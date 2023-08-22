public class Task {
    private String title;
    private Boolean done;

    public Task(String title) {
        this.title = title;
        this.done = false;
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
