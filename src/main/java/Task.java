public class Task {
    private Ui ui = new Ui();
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
        ui.showMark(this);
    }
    public void unmark() {
        this.done = false;
        ui.showUnmark(this);
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