package max.tasks;

public class Task {
    private String item;
    private boolean isDone;
    public Task(String item) {
        this.item = item;
        this.isDone = false;
    }
    public Task(String item, boolean isDone) {
        this.item = item;
        this.isDone = isDone;
    }
    public void mark() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }
    @Override
    public String toString() {
        String doneStatus = this.isDone ? "X" : " ";
        return "[" + doneStatus + "] " + item;
    }
    public String saveItem() {
        String number = isDone ? "1" : "0";
        return number + " | " + item;
    }
}