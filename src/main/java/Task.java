public class Task {
    protected String name;
    protected boolean done;

    public Task (String name) {
        this.name = name;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String getStatus() {
        return this.done ? "[X]" : "[ ]";
    }

    public String toPrint() {
        String str = this.getStatus() + " " + this.name;
        return str;
    }
}
