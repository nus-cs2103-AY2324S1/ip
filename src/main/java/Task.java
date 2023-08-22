public class Task {
    private boolean finish;
    private String task;

    Task (String task) {
        this.finish = false;
        this.task = task;
    }

    public boolean done() {
        return this.finish;
    }

    public void markDone() {
        this.finish = true;
    }

    public void markUndone() {
        this.finish = false;
    }

    @Override
    public String toString() {
        if (this.finish) {
            return "[x] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
