public class Task {
    private String item;
    private boolean done;
    public Task(String item) {
        this.item = item;
        this.done = false;
    }
    public void mark() {
        this.done = true;
        System.out.println("     Good job on completing your task!");
        System.out.println("       " + this);
    }
    public void unmark() {
        this.done = false;
        System.out.println("     Okay, I've marked this as not done yet!");
        System.out.println("       " + this);
    }

    @Override
    public String toString() {
        String doneStatus = this.done ? "X" : " ";
        return "[" + doneStatus + "] " + item;
    }
}