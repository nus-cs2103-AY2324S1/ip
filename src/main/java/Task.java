public class Task {

    private boolean isDone;
    private String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void toggleDone() {
        this.isDone = !isDone;
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + name : "[ ] " + name;
    }

}
