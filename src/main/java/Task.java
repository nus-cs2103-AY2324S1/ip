public class Task {
    private boolean done;
    private final String name;

    public Task(String name) {
        this.name = name;
        done = false;
    }

    public void setdone() {
        done = true;
    }

    @Override
    public String toString() {
        String box;

        if (done) {
            box = "[X] ";
        } else {
            box = "[ ] ";
        }

        return box + name;
    }
}
