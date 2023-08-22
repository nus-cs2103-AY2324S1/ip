public class Task {
    private String name;
    private boolean complete;

    public Task(String name) {
        this.name = name;
        this.complete = false;
    }

    public void taskDone() {
        this.complete = true;
    }

    public void taskUndone() {
        this.complete = false;
    }

    public String printTask() {
        return complete ? "[X] " + name : "[ ] " + name;
    }
}
