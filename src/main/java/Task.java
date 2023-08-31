public class Task {
    private String item;
    private boolean done;
    public Task(String item) {
        this.item = item;
        this.done = false;
    }
    public Task(String item, boolean done) {
        this.item = item;
        this.done = done;
    }
    public void mark() {
        this.done = true;
    }
    public void unmark() {
        this.done = false;
    }
    @Override
    public String toString() {
        String doneStatus = this.done ? "X" : " ";
        return "[" + doneStatus + "] " + item;
    }
    public String saveItem() {
        String number = done ? "1" : "0";
        return number + " | " + item;
    }
}