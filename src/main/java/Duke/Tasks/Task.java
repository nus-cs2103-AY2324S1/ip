package Duke.Tasks;

public class Task {
    private Boolean done = false;

    private String task;

    public Task(String input) {
        task = input;
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
            return "[X] " + "| " + task;
        } else {
            return "[ ] " +"| " + task;
        }
    }

}
