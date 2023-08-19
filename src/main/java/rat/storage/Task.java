package rat.storage;

public class Task {

    private String name;
    private boolean isDone;

    protected Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    protected void markDone() {
        this.isDone = true;
    }

    protected void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + this.name;
    }

}
