public class Task {
    private boolean done;
    private final String name;

    private Task() {
        this.name = "";
        done = false;
    }

    public Task(String name) {
        this.name = name;
        done = false;
    }

    public void setdone() {
        done = true;
    }

    public boolean isdone() {
        return this.done;
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
