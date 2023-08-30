package duke;

public abstract class Task {

    private final String name;
    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone(boolean display) {
        this.done = true;
        if (display) {
            System.out.println("\nNice! I've marked this task as done:\n  " + this + "\n");
        }
    }

    public void unmarkAsDone(boolean display) {
        this.done = false;
        if (display) {
            System.out.println("\nNice! I've unmarked this task as done:\n  " + this + "\n");
        }
    }

    @Override
    public String toString() {
        char markDone = this.done ? 'X' : ' ';
        return "[" + markDone + "] " + this.name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.done;
    }

    abstract String convertTaskToString();

}
