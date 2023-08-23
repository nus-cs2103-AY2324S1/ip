public class Task {
    private Boolean done;

    private String task;

    public Task(String input) {
        task = input;
        done = false;
    }

    public void markDone() {
        done = true;
    }

    public void markUndone() {
        done = false;
    }

    protected boolean isDone() {
        return done;
    }

    public String toString() {
        if (done) {
            return "[X] " + task;
        } else {
            return "[ ] " + task;
        }
    }

}
