public class Task {
    private boolean done;
    private String name;

    public Task(String name) {
        this.done = false;
        this.name = name;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.done = isDone;
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

    public String toDataString() {
        String isDone = "0";
        if (this.done) {
            isDone = "1";
        }
        return isDone + "|" + this.name;
    }
}
