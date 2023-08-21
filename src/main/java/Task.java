public class Task {
    private String description;

    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X": " ";
    }

    public void mark() {
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done: \n" +
                "\t\t" + toString());
    }

    public void unmark() {
        this.isDone = false;
        System.out.println("\tOK, I've marked this task as not done yet: \n" +
                "\t\t" + toString());
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
