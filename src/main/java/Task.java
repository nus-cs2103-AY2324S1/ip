public class Task {
    private Boolean done;

    private String name;

    public Task(String input) {
        name = input;
        done = false;
    }

    public void markDone() {
        done = true;
    }

    public void markUndone() {
        done = false;
    }

    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }

}
