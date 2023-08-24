public class Task {
    private boolean done;
    private String name;

    public Task(String name) {
        this.done = false;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean getDone() {
        return this.done;
    }

    public void markDone() {
        this.done = true;
    }

    public void markUnDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String doneCheckbox = "";
        if (this.done) {
            doneCheckbox = "[X]";
        } else {
            doneCheckbox = "[ ]";
        }
        return doneCheckbox + " " + this.name;
    }
}
