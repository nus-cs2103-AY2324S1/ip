public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    private void mark(){
        isDone = true;
    }

    private void unmark(){
        isDone = false;
    }

    public String description(int m) {
        String s;
        if (m == 0) {
            // mark
            mark();
            s = this.toString();
        } else {
            unmark();
            s = this.toString();
        }
        return s;
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
